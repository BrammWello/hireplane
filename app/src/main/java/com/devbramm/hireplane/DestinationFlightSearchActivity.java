package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.devbramm.hireplane.Adapters.DepartureCityAdapter;
import com.devbramm.hireplane.Adapters.DestinationCityAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DestinationFlightSearchActivity extends AppCompatActivity {

    EditText destinationTextInput;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    public static RecyclerView recyclerView;
    ArrayList<String> cityNameList;
    ArrayList<String> cityIATACodeList;
    ArrayList<String> airportNameList;
    DestinationCityAdapter destinationCityAdapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DestinationFlightSearchActivity.this.finish();
        overridePendingTransition(R.anim.fade_in_resume_activity,R.anim.fade_out_move_down_activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_flight_search);

        destinationTextInput = findViewById(R.id.depart_search_input);

        recyclerView = findViewById(R.id.dept_city_recycler);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("airports");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        cityNameList = new ArrayList<>();
        cityIATACodeList = new ArrayList<>();
        airportNameList = new ArrayList<>();

        destinationTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                    findViewById(R.id.searching_dest_progress).setVisibility(View.VISIBLE);
                } else {
                    /*
                     * Clear the list when editText is empty
                     * */
                    cityNameList.clear();
                    cityIATACodeList.clear();
                    airportNameList.clear();
                    recyclerView.removeAllViews();
                    destinationTextInput.clearFocus();
                    recyclerView.setVisibility(View.INVISIBLE);
                    findViewById(R.id.searching_dest_progress).setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void setAdapter(final String searchedString) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                 * Clear the list for every new search
                 * */
                cityNameList.clear();
                cityIATACodeList.clear();
                airportNameList.clear();
                recyclerView.removeAllViews();

                int counter = 0;

                /*
                 * Search all cities for matching searched string
                 * */
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String city_name = snapshot.child("city_airport").getValue(String.class);
                    String iata_code = snapshot.child("iata_code").getValue(String.class);
                    String airport_name = snapshot.child("country").getValue(String.class);

                    if (city_name.toLowerCase().contains(searchedString.toLowerCase())) {
                        cityNameList.add(city_name);
                        cityIATACodeList.add(iata_code);
                        airportNameList.add(airport_name);
                        counter++;
                    } else if (airport_name.toLowerCase().contains(searchedString.toLowerCase())) {
                        cityNameList.add(city_name);
                        cityIATACodeList.add(iata_code);
                        airportNameList.add(airport_name);
                        counter++;
                    }

                    /*
                     * Get maximum of 15 searched results only
                     * */
                    if (counter == 1)
                        break;
                    recyclerView.setVisibility(View.VISIBLE);
                }
                Log.i("Just before attach","it reaches");
                destinationCityAdapter = new DestinationCityAdapter(DestinationFlightSearchActivity.this, cityNameList, cityIATACodeList, airportNameList);
                recyclerView.setAdapter(destinationCityAdapter);
                findViewById(R.id.searching_dest_progress).setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
