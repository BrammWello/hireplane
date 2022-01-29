package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OnBoardingActivity extends AppCompatActivity {

    ImageView flyingAeroplane, cloudsForm, rangeRover, cityBack, worldMap, mapMarkers;
    TextView pagerName, pagerContent;

    Button nextPageBtn, skipBtn;

    ConstraintLayout dotOne, dotTwo, dotThree;

    Animation fadeIn, fadeOut, leftToRight, leftToRightOut, fadeInMoveUp, fadeOutMoveDown, rightToleft, rightToLeftOut, fadeInOffset;

    int dotCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        nextPageBtn = findViewById(R.id.ob_next_page_btn);
        flyingAeroplane = findViewById(R.id.flying_plane);
        cloudsForm = findViewById(R.id.clouds_form);
        rangeRover = findViewById(R.id.range_rover);
        cityBack = findViewById(R.id.city_back);
        worldMap = findViewById(R.id.world_map);
        mapMarkers = findViewById(R.id.map_markers);
        pagerName = findViewById(R.id.ob_pager_name);
        pagerContent = findViewById(R.id.ob_pager_content);
        dotOne = findViewById(R.id.page_dot_one);
        dotTwo = findViewById(R.id.page_dot_two);
        dotThree = findViewById(R.id.page_dot_three);

        //load the first set of items
        pagerName.setText("Get Booked, Fast!");
        pagerContent.setText("Hireplane ensures your travel priorities are met faster and get you high in no time");

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_ob_items);
        cloudsForm.setVisibility(View.VISIBLE);
        cloudsForm.startAnimation(fadeIn);

        leftToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_right_plane_ob);
        flyingAeroplane.setVisibility(View.VISIBLE);
        flyingAeroplane.startAnimation(leftToRight);

        fadeInMoveUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_move_up_ob_text);
        pagerName.setVisibility(View.VISIBLE);
        pagerContent.setVisibility(View.VISIBLE);
        pagerName.startAnimation(fadeInMoveUp);
        pagerContent.startAnimation(fadeInMoveUp);

        dotOne.setBackgroundResource(R.drawable.pager_long_dots_back);
        final ConstraintLayout.LayoutParams paramsDotOneIn = (ConstraintLayout.LayoutParams) dotOne.getLayoutParams();
        paramsDotOneIn.width = 48;
        dotOne.setLayoutParams(paramsDotOneIn);

        dotCounter = 1;



        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dotCounter == 1) {
                    //its the first page so remove the first page things
                    leftToRightOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_right_plane_out_ob);
                    flyingAeroplane.startAnimation(leftToRightOut);
                    flyingAeroplane.setVisibility(View.INVISIBLE);

                    fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_ob_items);
                    cloudsForm.startAnimation(fadeOut);
                    cloudsForm.setVisibility(View.INVISIBLE);

                    //remove the dot counter
                    dotOne.setBackgroundResource(R.drawable.pager_small_dots_back);
                    ConstraintLayout.LayoutParams paramsDotOneOut = (ConstraintLayout.LayoutParams) dotOne.getLayoutParams();
                    paramsDotOneOut.width = paramsDotOneIn.height;
                    dotOne.setLayoutParams(paramsDotOneOut);

                    //load in the second page things
                    pagerName.setText("Secure Extra Services, Easily!");
                    pagerContent.setText("With Hireplane, you don't have to worry about transit or hotel bookings during your journey anymore, it is our pleasure");

                    fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_ob_items);
                    cityBack.setVisibility(View.VISIBLE);
                    cityBack.startAnimation(fadeIn);

                    rightToleft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_left_range_rover_ob);
                    rangeRover.setVisibility(View.VISIBLE);
                    rangeRover.startAnimation(rightToleft);

                    fadeInMoveUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_move_up_ob_text);
                    pagerName.setVisibility(View.VISIBLE);
                    pagerContent.setVisibility(View.VISIBLE);
                    pagerName.startAnimation(fadeInMoveUp);
                    pagerContent.startAnimation(fadeInMoveUp);

                    //set dot two
                    dotTwo.setBackgroundResource(R.drawable.pager_long_dots_back);
                    ConstraintLayout.LayoutParams paramsDotTwoIn = (ConstraintLayout.LayoutParams) dotTwo.getLayoutParams();
                    paramsDotTwoIn.width = 48;
                    dotTwo.setLayoutParams(paramsDotTwoIn);

                    dotCounter = 2;

                } else if (dotCounter == 2) {
                    //its the second page so remove the second page things
                    rightToLeftOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_left_range_rover_out_ob);
                    rangeRover.startAnimation(rightToLeftOut);
                    rangeRover.setVisibility(View.INVISIBLE);

                    fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_ob_items);
                    cityBack.startAnimation(fadeOut);
                    cityBack.setVisibility(View.INVISIBLE);

                    //remove the dot counter
                    dotTwo.setBackgroundResource(R.drawable.pager_small_dots_back);
                    ConstraintLayout.LayoutParams paramsDotTwoOut = (ConstraintLayout.LayoutParams) dotTwo.getLayoutParams();
                    paramsDotTwoOut.width = paramsDotOneIn.height;
                    dotTwo.setLayoutParams(paramsDotTwoOut);

                    //load in the third page things
                    pagerName.setText("Around the world, in 80 days!");
                    pagerContent.setText("Lets' begin the adventure as you plan your flights, hotels and holidays immediately. What are you waiting for?");

                    fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_ob_items);
                    worldMap.setVisibility(View.VISIBLE);
                    worldMap.startAnimation(fadeIn);

                    fadeInOffset = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_map_markers_ob);
                    mapMarkers.setVisibility(View.VISIBLE);
                    mapMarkers.startAnimation(fadeInOffset);

                    fadeInMoveUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_move_up_ob_text);
                    pagerName.setVisibility(View.VISIBLE);
                    pagerContent.setVisibility(View.VISIBLE);
                    pagerName.startAnimation(fadeInMoveUp);
                    pagerContent.startAnimation(fadeInMoveUp);

                    //set dot two
                    dotThree.setBackgroundResource(R.drawable.pager_long_dots_back);
                    ConstraintLayout.LayoutParams paramsDotThreeIn = (ConstraintLayout.LayoutParams) dotThree.getLayoutParams();
                    paramsDotThreeIn.width = 48;
                    dotThree.setLayoutParams(paramsDotThreeIn);

                    dotCounter = 3;
                } else if (dotCounter == 3) {
                    // all pages have been loaded
                    startActivity(new Intent(OnBoardingActivity.this, HomeActivity.class));
                }

            }
        });

    }

    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.fragment_frame, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
