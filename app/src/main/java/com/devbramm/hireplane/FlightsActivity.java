package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FlightsActivity extends AppCompatActivity {

    public static TextView originAirportName, destinationAirportName;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        FlightsActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        originAirportName = findViewById(R.id.origin_airport_input);
        destinationAirportName = findViewById(R.id.destination_input);

        originAirportName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(FlightsActivity.this, AirportDepatureSearchActivity.class));
                    overridePendingTransition(R.anim.fade_in_move_up_activity, R.anim.fade_out_search_activity);

            }
        });

        destinationAirportName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (originAirportName.getText().toString().equals("")) {
                    Toast.makeText(FlightsActivity.this, "Please choose an Airport first", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(FlightsActivity.this, DestinationFlightSearchActivity.class));
                    overridePendingTransition(R.anim.fade_in_move_up_activity, R.anim.fade_out_search_activity);
                }
            }
        });
    }

}
