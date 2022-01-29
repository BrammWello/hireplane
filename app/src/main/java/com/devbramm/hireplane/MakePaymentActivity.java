package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MakePaymentActivity extends AppCompatActivity {

    Button purchaseTicketsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        findViewById(R.id.purchase_tickets_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MakePaymentActivity.this, TicketSuccessActivity.class));
            }
        });
    }
}
