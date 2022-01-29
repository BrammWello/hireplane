package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbramm.hireplane.Common.CommonUtils;

public class FlightSearchActivity extends AppCompatActivity {

    TextView originTravelAirport, destinationTravelAirport;

    TextView travelClassSelection, travelDatesInput;
    Button oneWayBtn, roundTripBtn;

    ConstraintLayout blueBackLayout;

    ImageView iconFromInput, iconLineOne, iconToInput, iconLineTwo, iconModeInput, iconLineThree, iconDateInput, iconLineFour, iconClassInput;

    Animation fadeOut, fadeIn, fadeInDelayed;

    Button searchFlightBtn;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();

        CommonUtils.flightDestinationAirportCode = "";
        CommonUtils.flightDestinationAirport = "";
        CommonUtils.flightDepatureAirportCode = "";
        CommonUtils.flightDepatureAirport = "";
        CommonUtils.flightModeTimeline = "";
        CommonUtils.flightDepatureDate = "";
        CommonUtils.flightReturnDate = "";
        CommonUtils.flightClassSelected = "";
        CommonUtils.flightAgeAdult = 0;
        CommonUtils.flightAgeSenior = 0;
        CommonUtils.flightAgeTeen = 0;
        CommonUtils.flightAgeChild = 0;
        CommonUtils.flightAgeInfant = 0;

        startActivity(new Intent(FlightSearchActivity.this,FlightsActivity.class));
        overridePendingTransition(R.anim.fade_in_resume_activity,R.anim.fade_out_move_down_activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        originTravelAirport = findViewById(R.id.origin_travel_airport);
        destinationTravelAirport = findViewById(R.id.destination_travel_airport);
        oneWayBtn = findViewById(R.id.one_way_btn);
        roundTripBtn = findViewById(R.id.round_trip_btn);
        travelDatesInput = findViewById(R.id.travel_dates_input);
        iconFromInput = findViewById(R.id.icon_from_input);
        iconLineOne = findViewById(R.id.icon_line_one);
        iconToInput = findViewById(R.id.icon_to_input);
        iconLineTwo = findViewById(R.id.icon_line_two);
        iconModeInput = findViewById(R.id.icon_mode_input);
        iconLineThree = findViewById(R.id.icon_line_three);
        iconDateInput = findViewById(R.id.icon_date_input);
        iconLineFour = findViewById(R.id.icon_line_four);
        iconClassInput = findViewById(R.id.icon_class_input);
        travelClassSelection = findViewById(R.id.travel_class_input);
        searchFlightBtn = findViewById(R.id.search_flight_details_btn);
        blueBackLayout = findViewById(R.id.blue_back_layout);

        originTravelAirport.setText("(" + CommonUtils.flightDepatureAirportCode + ") " + CommonUtils.flightDepatureAirport);
        destinationTravelAirport.setText("(" + CommonUtils.flightDestinationAirportCode + ") " + CommonUtils.flightDestinationAirport);

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_move_up_activity);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        fadeInDelayed = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_slight_delay);

        if (CommonUtils.flightDepatureDate == "") {
            findViewById(R.id.one_way_btn).setVisibility(View.VISIBLE);
            findViewById(R.id.round_trip_btn).setVisibility(View.VISIBLE);
            findViewById(R.id.one_way_btn).startAnimation(fadeInDelayed);
            findViewById(R.id.round_trip_btn).startAnimation(fadeInDelayed);
        } else {
            findViewById(R.id.one_way_btn).setVisibility(View.VISIBLE);
            findViewById(R.id.round_trip_btn).setVisibility(View.VISIBLE);
        }

        if (CommonUtils.flightModeTimeline == "One Way") {
            oneWayBtn.setBackgroundResource(R.drawable.button_back_dark_blue);
            oneWayBtn.setTextColor(getResources().getColor(R.color.colorTextWhite));
            roundTripBtn.setBackgroundResource(R.drawable.button_stroke_back);
            roundTripBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));

            iconLineThree.setBackgroundResource(R.drawable.dotted_line_blue);
            iconModeInput.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
        } else if (CommonUtils.flightModeTimeline == "Round Trip") {
            oneWayBtn.setBackgroundResource(R.drawable.button_stroke_back);
            oneWayBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
            roundTripBtn.setBackgroundResource(R.drawable.button_back_dark_blue);
            roundTripBtn.setTextColor(getResources().getColor(R.color.colorTextWhite));

            iconLineThree.setBackgroundResource(R.drawable.dotted_line_blue);
            iconModeInput.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
        }

        if (!(CommonUtils.flightDepatureDate == "") && CommonUtils.flightReturnDate == "") {
            travelDatesInput.setText("Leaving on: " + CommonUtils.flightDepatureDate);
            travelDatesInput.setVisibility(View.VISIBLE);

            findViewById(R.id.class_search_btn).setVisibility(View.VISIBLE);
            findViewById(R.id.travel_class_input).setVisibility(View.VISIBLE);
            findViewById(R.id.travel_class_input).startAnimation(fadeIn);

            iconDateInput.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
            iconLineFour.setBackgroundResource(R.drawable.dotted_line_blue);
        } else if (!(CommonUtils.flightDepatureDate == "") && !(CommonUtils.flightReturnDate == "")) {
            travelDatesInput.setText("From: " + CommonUtils.flightDepatureDate + ", To:" + CommonUtils.flightReturnDate);
            travelDatesInput.setVisibility(View.VISIBLE);
            findViewById(R.id.class_search_btn).setVisibility(View.VISIBLE);
            findViewById(R.id.travel_class_input).setVisibility(View.VISIBLE);
            findViewById(R.id.travel_class_input).startAnimation(fadeIn);
            iconDateInput.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
            iconLineFour.setBackgroundResource(R.drawable.dotted_line_blue);
        }

        iconFromInput.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
        iconLineOne.setBackgroundResource(R.drawable.dotted_line_blue);
        iconToInput.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
        iconLineTwo.setBackgroundResource(R.drawable.dotted_line_blue);

        oneWayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.flightModeTimeline = "One Way";
                oneWayBtn.setBackgroundResource(R.drawable.button_back_dark_blue);
                oneWayBtn.setTextColor(getResources().getColor(R.color.colorTextWhite));
                roundTripBtn.setBackgroundResource(R.drawable.button_stroke_back);
                roundTripBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));

                iconModeInput.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                iconLineThree.setBackgroundResource(R.drawable.dotted_line_blue);

                travelDatesInput.setVisibility(View.VISIBLE);
                findViewById(R.id.date_select_btn).setVisibility(View.VISIBLE);
                travelDatesInput.startAnimation(fadeIn);
            }
        });

        roundTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.flightModeTimeline = "Round Trip";
                oneWayBtn.setBackgroundResource(R.drawable.button_stroke_back);
                oneWayBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                roundTripBtn.setBackgroundResource(R.drawable.button_back_dark_blue);
                roundTripBtn.setTextColor(getResources().getColor(R.color.colorTextWhite));

                iconModeInput.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                iconLineThree.setBackgroundResource(R.drawable.dotted_line_blue);

                travelDatesInput.setVisibility(View.VISIBLE);
                findViewById(R.id.date_select_btn).setVisibility(View.VISIBLE);
                travelDatesInput.startAnimation(fadeIn);
            }
        });

        findViewById(R.id.class_search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FlightSearchActivity.this, ClassTravellerPickerActivity.class));
                overridePendingTransition(R.anim.fade_in_move_up_activity,R.anim.fade_out_search_activity);
            }
        });

        findViewById(R.id.date_select_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FlightSearchActivity.this, TravelDatePickerActivity.class));
                overridePendingTransition(R.anim.fade_in_move_up_activity,R.anim.fade_out_search_activity);
            }
        });

        //check if class has been selected
        if (!(CommonUtils.flightClassSelected == "")) {
            travelClassSelection.setText(CommonUtils.flightClassSelected + ": " + CommonUtils.flightAgeAdult + " adults, " + CommonUtils.flightAgeSenior + " seniors, " + CommonUtils.flightAgeTeen + " teens, " + CommonUtils.flightAgeChild + " child, " + CommonUtils.flightAgeInfant + " infants");
            iconClassInput.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
            searchFlightBtn.setVisibility(View.VISIBLE);
        }

        searchFlightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the runtime version is at least Lollipop
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // get the center for the clipping circle
                    int cx = blueBackLayout.getWidth() / 2;
                    int cy = blueBackLayout.getHeight() * 2;

                    // get the final radius for the clipping circle
                    float finalRadius = (float) Math.hypot(cx, cy);

                    // create the animator for this view (the start radius is zero)
                    Animator anim = ViewAnimationUtils.createCircularReveal(blueBackLayout, cx, cy, 0f, finalRadius);

                    // make the view visible and start the animation
                    blueBackLayout.setVisibility(View.VISIBLE);
                    anim.start();
                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            finish();
                            startActivity(new Intent(FlightSearchActivity.this,AirlineSelectionActivity.class));
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                } else {
                    // set the view to invisible without a circular reveal animation below Lollipop
                    blueBackLayout.setVisibility(View.INVISIBLE);
                }

            }
        });

    }
}
