package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devbramm.hireplane.Common.CommonUtils;

public class TicketDetailsActivity extends AppCompatActivity {

    TextView pageTitle, airlineNumber1, airlineNumber2, ticketPrice, airlineName, deptCityCodeOne, deptCityNameOne,deptCityCodeTwo, deptCityNameTwo, destCityCode, destCityName, deptTimeOne, deptTimeTwo, destTime, deptTimeDuration, destTimeDuration, deptDate, destDate, flightBaggageDetailsBtn;
    ImageView airlineLogo;
    Button closeFlightDetailsLayout, proceedToBookBtn;
    ConstraintLayout flightBaggageDetailsLayout, returnTicketDetails;

    Animation fadeInMoveUp, fadeOutMoveDown;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);

        pageTitle = findViewById(R.id.ticket_details_city_title);
        airlineLogo = findViewById(R.id.airline_logo);
        airlineNumber1 = findViewById(R.id.airline_number);
        airlineNumber2 = findViewById(R.id.airline_number_2);
        ticketPrice = findViewById(R.id.ticket_price);
        airlineName = findViewById(R.id.airline_name);
        deptCityCodeOne = findViewById(R.id.dept_ticket_city_code_one);
        deptCityNameOne = findViewById(R.id.dept_ticket_city_name_one);
        deptCityCodeTwo = findViewById(R.id.dept_ticket_city_code_two);
        deptCityNameTwo = findViewById(R.id.dept_ticket_city_name_two);
        deptTimeOne = findViewById(R.id.dept_ticket_time_one);
        deptTimeTwo = findViewById(R.id.dept_ticket_time_two);
        destCityCode = findViewById(R.id.dest_ticket_city_code);
        destCityName = findViewById(R.id.dest_ticket_city_name);
        destTime = findViewById(R.id.dest_ticket_time);
        deptTimeDuration = findViewById(R.id.dept_ticket_duration);
        destTimeDuration = findViewById(R.id.dest_ticket_duration);
        deptDate = findViewById(R.id.dept_ticket_date);
        destDate = findViewById(R.id.dest_ticket_date);
        flightBaggageDetailsBtn = findViewById(R.id.flight_baggage_details_btn);
        flightBaggageDetailsLayout = findViewById(R.id.flight_baggage_details_layout);
        closeFlightDetailsLayout = findViewById(R.id.flight_baggage_details_layout_close_btn);
        proceedToBookBtn = findViewById(R.id.proceed_to_book_btn);
        returnTicketDetails = findViewById(R.id.return_ticket_details);


        pageTitle.setText(CommonUtils.flightDepatureAirportCode + "  -  " + CommonUtils.flightDestinationAirportCode);
        airlineNumber1.setText(CommonUtils.ticketAirlineCode);
        airlineName.setText(CommonUtils.ticketAirlineName);
        deptCityCodeOne.setText(CommonUtils.flightDepatureAirportCode);
        deptCityCodeTwo.setText(CommonUtils.flightDestinationAirportCode);
        deptCityNameOne.setText(CommonUtils.flightDepatureAirport);
        deptCityNameTwo.setText(CommonUtils.flightDestinationAirport);
        deptTimeOne.setText(CommonUtils.ticketAirlineLeavingTime);
        deptTimeTwo.setText(CommonUtils.ticketAirlineArrivalTime);
        deptTimeDuration.setText(CommonUtils.ticketAirlineDurationTime);
        deptDate.setText(CommonUtils.flightDepatureDate);

        if (CommonUtils.flightModeTimeline == "Round Trip") {
            returnTicketDetails.setVisibility(View.VISIBLE);
            airlineNumber2.setText(CommonUtils.ticketAirlineCode);
            destCityCode.setText(CommonUtils.flightDestinationAirportCode);
            destCityName.setText(CommonUtils.flightDestinationAirport);
            destTime.setText(CommonUtils.ticketAirlineArrivalTime);
            destTimeDuration.setText(CommonUtils.ticketAirlineDurationTime);
            destDate.setText(CommonUtils.flightReturnDate);
        }

        ticketPrice.setText("KES " + CommonUtils.ticketAirlinePrice);

        Glide.with(this).load(CommonUtils.ticketAirlineLogo).into(airlineLogo);

        flightBaggageDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeInMoveUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_move_up_ob_text);
                flightBaggageDetailsLayout.setVisibility(View.VISIBLE);
                flightBaggageDetailsLayout.startAnimation(fadeInMoveUp);
            }
        });

        closeFlightDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeOutMoveDown= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_move_down_ob_text);
                flightBaggageDetailsLayout.startAnimation(fadeOutMoveDown);
                flightBaggageDetailsLayout.setVisibility(View.INVISIBLE);
            }
        });

        proceedToBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TicketDetailsActivity.this,BookingDetailsActivity.class));
            }
        });
    }
}
