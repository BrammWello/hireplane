package com.devbramm.hireplane;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeActivity extends AppCompatActivity {

    ConstraintLayout mainHomeLayout, flightsBtnLayout;

    Animation moveUp;

    View homeCircularRevealView;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //TODO put a 2 step button exit

    }

    @Override
    protected void onResume() {
        super.onResume();
        //cater for onBack pressed
        homeCircularRevealView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_home);



        mainHomeLayout = findViewById(R.id.main_home_layout);
        flightsBtnLayout = findViewById(R.id.flight_btn_layout);
        homeCircularRevealView = findViewById(R.id.home_circular_reveal_view);

        moveUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_move_up_ob_text);
        mainHomeLayout.startAnimation(moveUp);
        mainHomeLayout.setVisibility(View.VISIBLE);

////
//        final int screenWidth = getResources().getDisplayMetrics().widthPixels;
//        final int screenHeight = getResources().getDisplayMetrics().heightPixels;
////        paramsScreenSize.width = screenWidth;
//        paramsScreenSize.height = screenHeight;

        flightsBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ViewGroup.LayoutParams buttonSize =  flightsBtnLayout.getLayoutParams();
//                buttonSize.width = screenWidth;
//                buttonSize.height = screenHeight;
//                flightsBtnLayout.setLayoutParams(buttonSize);
//                Toast.makeText(HomeActivity.this, "Clicked with" + paramsScreenSize.width + "width and " + paramsScreenSize.height, Toast.LENGTH_SHORT).show();

                homeCircularRevealView.setBackgroundResource(R.drawable.card_blue_gradient);

                // Check if the runtime version is at least Lollipop
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // get the center for the clipping circle
                    int cx = homeCircularRevealView.getWidth() / 2;
                    int cy = homeCircularRevealView.getHeight() / 2;

                    // get the final radius for the clipping circle
                    float finalRadius = (float) Math.hypot(homeCircularRevealView.getWidth() - cx, homeCircularRevealView.getHeight() + cy);

                    // create the animator for this view (the start radius is zero)
                    Animator anim = ViewAnimationUtils.createCircularReveal(homeCircularRevealView, cx, cy, 0f, finalRadius);

                    // make the view visible and start the animation
                    homeCircularRevealView.setVisibility(View.VISIBLE);
                    anim.setDuration(700);
                    anim.start();
                    startActivity(new Intent(HomeActivity.this,FlightsActivity.class));
                    overridePendingTransition(R.anim.fade_in_new_activity,R.anim.fade_out_activity);

                } else {
                    // set the view to invisible without a circular reveal animation below Lollipop
                    homeCircularRevealView.setVisibility(View.INVISIBLE);
                }


            }
        });
    }
}
