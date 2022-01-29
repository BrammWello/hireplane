package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BookingDetailsActivity extends AppCompatActivity {

    CardView confirmPersonalDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        confirmPersonalDetails = findViewById(R.id.confirm_personal_details);

        confirmPersonalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingDetailsActivity.this, MakePaymentActivity.class));
            }
        });
    }
}
