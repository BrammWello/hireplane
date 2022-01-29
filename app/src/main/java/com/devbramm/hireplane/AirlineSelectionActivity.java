package com.devbramm.hireplane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devbramm.hireplane.Adapters.AirlineGetterAdapter;
import com.devbramm.hireplane.Common.CommonUtils;
import com.devbramm.hireplane.Helpers.TicketItemClickListener;
import com.devbramm.hireplane.Models.AirlineGetter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AirlineSelectionActivity extends AppCompatActivity {

    TextView deptDestTitle, searchResultNumber, loadingDeptCity, loadingDestCity;
    ImageView worldMapDottedOne, worldMapDottedTwo, loadingRoundAeroplanes;

    ConstraintLayout loadingFlightView;

    FirebaseDatabase database;
    DatabaseReference logosReference;
    FirebaseRecyclerAdapter<AirlineGetter, AirlineGetterAdapter> adapterAirline;
    RecyclerView airlineItemsRecycler;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapterAirline.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airline_selection);

        deptDestTitle = findViewById(R.id.dept_dest_title);
        searchResultNumber = findViewById(R.id.search_result_number);
        worldMapDottedOne = findViewById(R.id.world_map_dotted_one);
        worldMapDottedTwo = findViewById(R.id.world_map_dotted_two);
        loadingRoundAeroplanes = findViewById(R.id.loading_round_aeroplanes);
        loadingFlightView = findViewById(R.id.loading_view);
        loadingDeptCity = findViewById(R.id.loading_dept_city);
        loadingDestCity = findViewById(R.id.loading_dest_city);

        deptDestTitle.setText(CommonUtils.flightDepatureAirportCode + "  -  " + CommonUtils.flightDestinationAirportCode);
        loadingDeptCity.setText(CommonUtils.flightDepatureAirportCode);
        loadingDestCity.setText(CommonUtils.flightDestinationAirportCode);

        database = FirebaseDatabase.getInstance();
        logosReference = database.getReference("airlines");

        airlineItemsRecycler = findViewById(R.id.airlineGetterRecycler);
        airlineItemsRecycler.setLayoutManager(new LinearLayoutManager(this));

        setImageLoop();
        loadAddressesFromDB();
    }

    private void loadAddressesFromDB() {

        Random randomStartPosition = new Random();
        String fbStartRequest = String.valueOf(randomStartPosition.nextInt(61));
        int fbLimitRequest = (randomStartPosition.nextInt(20));
        final FirebaseStorage storage = FirebaseStorage.getInstance();

        searchResultNumber.setText(fbLimitRequest + " flights");

        Query airlinesQuery = logosReference.orderByKey().startAt(fbStartRequest).limitToFirst(fbLimitRequest);
        FirebaseRecyclerOptions airlinesOptions = new FirebaseRecyclerOptions.Builder<AirlineGetter>().setQuery(airlinesQuery,AirlineGetter.class).build();

        adapterAirline = new FirebaseRecyclerAdapter<AirlineGetter, AirlineGetterAdapter>(airlinesOptions) {
            @Override
            protected void onBindViewHolder(@NonNull AirlineGetterAdapter airlineGetterAdapter, int i, @NonNull final AirlineGetter airlineGetter) {
                final Random random = new Random();
                final Date time = new Time(random.nextLong());
                final int randomFlightTailCode = random.nextInt(100);

                airlineGetterAdapter.airlineItemName.setText(airlineGetter.getAirline_name());
                airlineGetterAdapter.airlineItemCode.setText(airlineGetter.getAirline_code() + "-" + randomFlightTailCode);
                airlineGetterAdapter.ticketDeptAirport.setText(CommonUtils.flightDepatureAirportCode);
                airlineGetterAdapter.ticketDestAirport.setText(CommonUtils.flightDestinationAirportCode);

                Glide.with(getBaseContext())
                        .load(airlineGetter.getAirline_logo())
                        .into(airlineGetterAdapter.airlineItemLogo);



                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String departureTime = sdf.format(time);

                try {
                    Random randomTravelTime = new Random();
                    int travelTimeDurationHours = randomTravelTime.nextInt(10);
                    int travelTimeDurationMinutes = randomTravelTime.nextInt(60);
                    Date date = sdf.parse(departureTime);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.HOUR, travelTimeDurationHours);
                    calendar.add(Calendar.MINUTE, travelTimeDurationMinutes);
                    airlineGetterAdapter.timeBetweenTravel.setText(travelTimeDurationHours + "h " + travelTimeDurationMinutes + "m");
                    airlineGetterAdapter.arrivalTimeTicket.setText(new SimpleDateFormat("HH:mm").format(calendar.getTime()));

                    CommonUtils.ticketAirlineArrivalTime = new SimpleDateFormat("HH:mm").format(calendar.getTime());
                    CommonUtils.ticketAirlineDurationTime = travelTimeDurationHours + "h " + travelTimeDurationMinutes + "m";
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.i("round", departureTime );

                String leavingTime = String.valueOf(time).substring(0,5);

                airlineGetterAdapter.leavingTimeTicket.setText(leavingTime);
                CommonUtils.ticketAirlineLeavingTime = leavingTime;

                //random price
                int min = 3000;
                int max = 100000;
                final String ticketPriceInKES = String.valueOf(random.nextInt(max - min + 1) + min);
                airlineGetterAdapter.ticketPrice.setText("KES " + ticketPriceInKES);


                airlineGetterAdapter.setTicketItemClickListener(new TicketItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent toTicketDetails = new Intent(AirlineSelectionActivity.this,TicketDetailsActivity.class);
                        startActivity(toTicketDetails);
                        //set ticket details for next page
                        CommonUtils.ticketAirlineLogo = airlineGetter.getAirline_logo();
                        CommonUtils.ticketAirlineCode = airlineGetter.getAirline_code() + "-" + randomFlightTailCode;
                        CommonUtils.ticketAirlineName = airlineGetter.getAirline_name();
                        CommonUtils.ticketAirlinePrice = ticketPriceInKES;

                    }
                });

            }

            @NonNull
            @Override
            public AirlineGetterAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.flight_tickets_search_item,parent,false);

                return new AirlineGetterAdapter(view);
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                loadingFlightView.setVisibility(View.INVISIBLE);
            }
        };
        adapterAirline.startListening();
        airlineItemsRecycler.setAdapter(adapterAirline);
    }

    private void setImageLoop() {

        worldMapDottedOne.post(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation outAnim =
                        new TranslateAnimation(loadingFlightView.getWidth(),
                                0f, 0f, 0f);
                //set the rotating planes too
                RotateAnimation rotateAnimation =
                        new RotateAnimation( 0,360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                // move from 0 (START) to width (PARENT_SIZE)
                outAnim.setInterpolator(new LinearInterpolator());
                outAnim.setRepeatMode(Animation.INFINITE); // repeat the animation
                outAnim.setRepeatCount(Animation.INFINITE);
                outAnim.setDuration(2000);

                TranslateAnimation inAnim =
                        new TranslateAnimation(
                                0f,- loadingFlightView.getWidth(), 0f, 0f);
                // move from out width (-PARENT_SIZE) to 0 (START)
                inAnim.setInterpolator(new LinearInterpolator());
                inAnim.setRepeatMode(Animation.INFINITE);
                inAnim.setRepeatCount(Animation.INFINITE);
                inAnim.setDuration(2000); // same duration as the first

                rotateAnimation.setDuration(900);
                rotateAnimation.setRepeatCount(Animation.INFINITE);

                worldMapDottedOne.startAnimation(outAnim); // start first anim
                worldMapDottedTwo.startAnimation(inAnim); // start second anim

                loadingRoundAeroplanes.startAnimation(rotateAnimation);

            }
        });



    }
}
