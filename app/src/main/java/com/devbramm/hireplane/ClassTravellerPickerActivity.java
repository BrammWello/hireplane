package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devbramm.hireplane.Common.CommonUtils;

public class ClassTravellerPickerActivity extends AppCompatActivity {

    Button economyClassBtn, proEconomyClassBtn, businessClassBtn, firstClassBtn;

    TextView adultCounter, seniorCounter, teenCounter, childCounter, infantCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_traveller_picker);

        adultCounter = findViewById(R.id.adult_counter_txt);
        seniorCounter = findViewById(R.id.senior_counter_txt);
        teenCounter = findViewById(R.id.teen_counter_txt);
        childCounter = findViewById(R.id.child_counter_txt);
        infantCounter = findViewById(R.id.infant_counter_txt);
        economyClassBtn = findViewById(R.id.economy_class_btn);
        proEconomyClassBtn = findViewById(R.id.pro_economy_class_btn);
        businessClassBtn = findViewById(R.id.business_class_btn);
        firstClassBtn = findViewById(R.id.first_class_btn);

        setButtonFunct();
    }

    private void setButtonFunct() {
        findViewById(R.id.class_traveller_confirm_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CommonUtils.flightAgeAdult == 0 && CommonUtils.flightAgeSenior == 0 && CommonUtils.flightAgeTeen == 0 && CommonUtils.flightAgeChild == 0 && CommonUtils.flightAgeInfant == 0) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Please select at least one Age Category", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(ClassTravellerPickerActivity.this, FlightSearchActivity.class));
                    overridePendingTransition(R.anim.fade_in_resume_activity, R.anim.fade_out_move_down_activity);
                }
            }
        });

        economyClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                economyClassBtn.setBackgroundResource(R.drawable.button_back_dark_blue);
                economyClassBtn.setTextColor(getResources().getColor(R.color.colorTextWhite));
                proEconomyClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                proEconomyClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                businessClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                businessClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                firstClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                firstClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                CommonUtils.flightClassSelected = "Economy Class";
            }
        });

        proEconomyClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proEconomyClassBtn.setBackgroundResource(R.drawable.button_back_dark_blue);
                proEconomyClassBtn.setTextColor(getResources().getColor(R.color.colorTextWhite));
                businessClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                businessClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                firstClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                firstClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                economyClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                economyClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                CommonUtils.flightClassSelected = "Pro Economy Class";
            }
        });

        businessClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessClassBtn.setBackgroundResource(R.drawable.button_back_dark_blue);
                businessClassBtn.setTextColor(getResources().getColor(R.color.colorTextWhite));
                firstClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                firstClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                economyClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                economyClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                proEconomyClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                proEconomyClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                CommonUtils.flightClassSelected = "Business Class";
            }
        });

        firstClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstClassBtn.setBackgroundResource(R.drawable.button_back_dark_blue);
                firstClassBtn.setTextColor(getResources().getColor(R.color.colorTextWhite));
                economyClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                economyClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                proEconomyClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                economyClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                businessClassBtn.setBackgroundResource(R.drawable.button_stroke_back);
                businessClassBtn.setTextColor(getResources().getColor(R.color.colorInactiveGrey));
                CommonUtils.flightClassSelected = "First Class";
            }
        });

        findViewById(R.id.adult_remove_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeAdult == 0) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeAdult -= 1;
                    adultCounter.setText(String.valueOf(CommonUtils.flightAgeAdult));
                }
            }
        });

        findViewById(R.id.adult_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeAdult == 15) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Maximum Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeAdult += 1;
                    adultCounter.setText(String.valueOf(CommonUtils.flightAgeAdult));
                }
            }
        });

        findViewById(R.id.senior_remove_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeSenior == 0) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeSenior -= 1;
                    seniorCounter.setText(String.valueOf(CommonUtils.flightAgeSenior));
                }
            }
        });

        findViewById(R.id.senior_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeSenior == 15) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Maximum Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeSenior += 1;
                    seniorCounter.setText(String.valueOf(CommonUtils.flightAgeSenior));
                }
            }
        });

        findViewById(R.id.teen_remove_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeTeen == 0) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeTeen -= 1;
                    teenCounter.setText(String.valueOf(CommonUtils.flightAgeTeen));
                }
            }
        });

        findViewById(R.id.teen_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeTeen == 15) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Maximum Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeTeen += 1;
                    teenCounter.setText(String.valueOf(CommonUtils.flightAgeTeen));
                }
            }
        });

        findViewById(R.id.child_remove_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeChild == 0) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeChild -= 1;
                    childCounter.setText(String.valueOf(CommonUtils.flightAgeChild));
                }
            }
        });

        findViewById(R.id.child_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeChild == 15) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Maximum Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeChild += 1;
                    childCounter.setText(String.valueOf(CommonUtils.flightAgeChild));
                }
            }
        });

        findViewById(R.id.infant_remove_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeInfant == 0) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeInfant -= 1;
                    infantCounter.setText(String.valueOf(CommonUtils.flightAgeInfant));
                }
            }
        });

        findViewById(R.id.infant_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.flightAgeInfant == 15) {
                    Toast.makeText(ClassTravellerPickerActivity.this, "Maximum Limit reached", Toast.LENGTH_SHORT).show();
                } else {
                    CommonUtils.flightAgeInfant += 1;
                    infantCounter.setText(String.valueOf(CommonUtils.flightAgeInfant));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ClassTravellerPickerActivity.this.finish();
        overridePendingTransition(R.anim.fade_in_resume_activity,R.anim.fade_out_move_down_activity);
    }
}
