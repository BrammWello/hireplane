package com.devbramm.hireplane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.devbramm.hireplane.Common.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TravelDatePickerActivity extends AppCompatActivity {

    TextView ddateTxt, rdateTxt,durationText,ddateChoice, rdateChoice, durationChoice;

    CalendarView ddateCalender, rdateCalender;

    Animation fadeOut, fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_date_picker);

        ddateCalender = findViewById(R.id.depart_calender);
        rdateCalender = findViewById(R.id.return_calender);
        ddateChoice = findViewById(R.id.ddate_choice);
        rdateChoice = findViewById(R.id.rdate_choice);
        durationChoice = findViewById(R.id.duration_choice);

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_resume_activity);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);

        findViewById(R.id.ddate_choice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.depart_calender_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.depart_calender_layout).startAnimation(fadeIn);
            }
        });

        findViewById(R.id.rdate_choice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.return_calender_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.return_calender_layout).startAnimation(fadeIn);
            }
        });

        Calendar calendar = Calendar.getInstance();
        //avoid previous dates than one specified
        final Calendar calendarForward = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        final long date = calendar.getTime().getTime();
        ddateCalender.setMinDate(date);
        ddateCalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                ddateChoice.setText("on " + dayOfMonth + "/" + (month + 1) + "/" + year);
                CommonUtils.flightDepatureDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                findViewById(R.id.depart_calender_layout).startAnimation(fadeOut);
                findViewById(R.id.depart_calender_layout).setVisibility(View.INVISIBLE);

                calendarForward.set(year,month,dayOfMonth);

                rdateCalender.setMinDate(calendarForward.getTime().getTime());

                if (CommonUtils.flightModeTimeline == "One Way") {
                    CommonUtils.flightReturnDate = "";
                    findViewById(R.id.date_picker_confirm_btn).setVisibility(View.VISIBLE);
                } else if (CommonUtils.flightModeTimeline == "Round Trip") {
                    findViewById(R.id.rdate_text).setVisibility(View.VISIBLE);
                    findViewById(R.id.rdate_choice).setVisibility(View.VISIBLE);
                }

                rdateCalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        rdateChoice.setText("to " + dayOfMonth + "/" + (month + 1) + "/" + year);
                        CommonUtils.flightReturnDate = dayOfMonth + "/" + (month + 1) + "/" + year;

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                        try {
                            Date date1 = sdf.parse(CommonUtils.flightDepatureDate);
                            Date date2 = sdf.parse(CommonUtils.flightReturnDate);
                            long daysDiff = date2.getTime() - date1.getTime();

                            durationChoice.setText(String.valueOf(TimeUnit.DAYS.convert(daysDiff,TimeUnit.MILLISECONDS)) + " days");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        findViewById(R.id.duration_text).setVisibility(View.VISIBLE);
                        findViewById(R.id.duration_choice).setVisibility(View.VISIBLE);
                        findViewById(R.id.date_picker_confirm_btn).setVisibility(View.VISIBLE);
                    }
                });

            }
        });


        findViewById(R.id.date_picker_confirm_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TravelDatePickerActivity.this, FlightSearchActivity.class));
                overridePendingTransition(R.anim.fade_in_resume_activity,R.anim.fade_out_move_down_activity);
            }
        });

        findViewById(R.id.close_pick_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelDatePickerActivity.this.finish();
                overridePendingTransition(R.anim.fade_in_resume_activity,R.anim.fade_out_move_down_activity);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TravelDatePickerActivity.this.finish();
        overridePendingTransition(R.anim.fade_in_resume_activity,R.anim.fade_out_move_down_activity);
    }
}
