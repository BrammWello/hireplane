package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    TextView splashAppName;
    ImageView imageOutlineIcon, splashCarIcon, splashRoadIcon, airplaneBackground, topPlaneIcon, leftPlaneIcon, cloudsIcon;
    Button letsGoBtn;
    ConstraintLayout bottomContentLayout, mainSplashBackground;

    Animation animMoveUp, animMoveDown, animMoveRight, animMoveLeft, animFadeInMoveUp, animFadeIn, animFadeInBottom, animMoveRightPlane, animMoveLeftClouds;

    Animation animFadeOut, animMovePlaneOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashAppName = findViewById(R.id.splash_screen_app_name);
        imageOutlineIcon = findViewById(R.id.image_outline_icon);
        splashCarIcon = findViewById(R.id.car_icon);
        splashRoadIcon = findViewById(R.id.road_icon);
        letsGoBtn = findViewById(R.id.lets_go_btn);
        airplaneBackground = findViewById(R.id.airplane_background);
        bottomContentLayout = findViewById(R.id.bottom_content_layout);
        leftPlaneIcon = findViewById(R.id.left_plane_icon);
        topPlaneIcon = findViewById(R.id.top_plane_icon);
        cloudsIcon = findViewById(R.id.clouds_icon);
        mainSplashBackground = findViewById(R.id.main_splash_background);

        //animate the flying aeroplane first
        animMoveUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_up);
        airplaneBackground.setVisibility(View.VISIBLE);
        airplaneBackground.startAnimation(animMoveUp);

        //fade in the bottom content
        animFadeInBottom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_bottom_content);
        bottomContentLayout.setVisibility(View.VISIBLE);
        bottomContentLayout.startAnimation(animFadeInBottom);

        //fade in bottom content, clouds and planes
        animFadeInMoveUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_move_up);
        splashAppName.setVisibility(View.VISIBLE);
        splashAppName.startAnimation(animFadeInMoveUp);

        animMoveDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_down);
        topPlaneIcon.setVisibility(View.VISIBLE);
        topPlaneIcon.startAnimation(animMoveDown);

        animMoveRightPlane = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_right_plane);
        leftPlaneIcon.setVisibility(View.VISIBLE);
        leftPlaneIcon.startAnimation(animMoveRightPlane);

        animMoveLeftClouds = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_left_clouds);
        cloudsIcon.setVisibility(View.VISIBLE);
        cloudsIcon.startAnimation(animMoveLeftClouds);

        //start the bottom content animations now
        animMoveLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_left);
        imageOutlineIcon.setVisibility(View.VISIBLE);
        splashRoadIcon.setVisibility(View.VISIBLE);
        imageOutlineIcon.startAnimation(animMoveLeft);
        splashRoadIcon.startAnimation(animMoveLeft);

        animMoveRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.car_move_right);
        splashCarIcon.setVisibility(View.VISIBLE);
        splashCarIcon.startAnimation(animMoveRight);
        splashCarIcon.setVisibility(View.INVISIBLE);

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        letsGoBtn.setVisibility(View.VISIBLE);
        letsGoBtn.startAnimation(animFadeIn);

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        letsGoBtn.setVisibility(View.VISIBLE);
        letsGoBtn.startAnimation(animFadeIn);

        //check if the user already opened the app
        SharedPreferences oneTimeSplashScreen = getSharedPreferences("prefs",MODE_PRIVATE);
        final boolean firstStart = oneTimeSplashScreen.getBoolean("firstTimeLogin", true);

        letsGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fade out bottom layout, planes and clouds
                animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                bottomContentLayout.startAnimation(animFadeOut);
                bottomContentLayout.setVisibility(View.INVISIBLE);

                topPlaneIcon.startAnimation(animFadeOut);
                topPlaneIcon.setVisibility(View.INVISIBLE);

                leftPlaneIcon.startAnimation(animFadeOut);
                leftPlaneIcon.setVisibility(View.INVISIBLE);

                cloudsIcon.startAnimation(animFadeOut);
                cloudsIcon.setVisibility(View.INVISIBLE);

                animMovePlaneOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_plane_out);
                airplaneBackground.startAnimation(animMovePlaneOut);
                airplaneBackground.setVisibility(View.INVISIBLE);

                //fade the background to white too
                TransitionDrawable transitionDrawable = (TransitionDrawable) mainSplashBackground.getBackground();
                transitionDrawable.startTransition(1000);

                if (firstStart) {
                    Intent toWalkthrough = new Intent(SplashScreenActivity.this, OnBoardingActivity.class);
                    startActivity(toWalkthrough);
                    overridePendingTransition(R.anim.fade_in_new_activity,R.anim.fade_out_activity);

                    //set the first time login as true
                    SharedPreferences oneTimeSplashScreen = getSharedPreferences("prefs",MODE_PRIVATE);
                    SharedPreferences.Editor editor = oneTimeSplashScreen.edit();
                    editor.putBoolean("firstTimeLogin", false);
                    editor.apply();
                } else {
                    Intent toHome = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(toHome);
                    overridePendingTransition(R.anim.fade_in_new_activity,R.anim.fade_out_activity);
                    finish();
                }
            }
        });

    }
}
