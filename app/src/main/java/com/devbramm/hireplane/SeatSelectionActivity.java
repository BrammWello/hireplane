package com.devbramm.hireplane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.devbramm.hireplane.Adapters.PlaneSeatsAdapter;
import com.devbramm.hireplane.Adapters.SeatSelectedAdapter;

import java.util.ArrayList;

public class SeatSelectionActivity extends AppCompatActivity {

    RecyclerView firstClassSideA, firstClassSideB, firstClassSideC, firstClassSideD, selectedSeatsRecycler;
    private ArrayList<String> seatNumbersA = new ArrayList<>();
    private ArrayList<String> seatNumbersB = new ArrayList<>();
    private ArrayList<String> seatNumbersC = new ArrayList<>();
    private ArrayList<String> seatNumbersD = new ArrayList<>();
    public static ArrayList<String> seatNumbersSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);


        loadRecyclerSeats();
    }

    private void loadRecyclerSeats() {

        for (int i = 1; i<29; i++ ) {
            seatNumbersA.add("A0" + i);
            seatNumbersB.add("B0" + i);
            seatNumbersC.add("C0" + i);
            seatNumbersD.add("D0" + i);
        }

        firstClassSideA = findViewById(R.id.first_class_side_A_recycler);
        firstClassSideB = findViewById(R.id.first_class_side_B_recycler);
        firstClassSideC = findViewById(R.id.first_class_side_C_recycler);
        firstClassSideD = findViewById(R.id.first_class_side_D_recycler);
        selectedSeatsRecycler = findViewById(R.id.selected_seats_recycler);

        firstClassSideA.setLayoutManager(new GridLayoutManager(this,4));
        firstClassSideB.setLayoutManager(new GridLayoutManager(this,4));
        firstClassSideC.setLayoutManager(new GridLayoutManager(this,4));
        firstClassSideD.setLayoutManager(new GridLayoutManager(this,4));
        selectedSeatsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        PlaneSeatsAdapter planeSeatsAdapterSideA = new PlaneSeatsAdapter(SeatSelectionActivity.this, seatNumbersA);
        firstClassSideA.setAdapter(planeSeatsAdapterSideA);

        PlaneSeatsAdapter planeSeatsAdapterSideB = new PlaneSeatsAdapter(SeatSelectionActivity.this, seatNumbersB);
        firstClassSideB.setAdapter(planeSeatsAdapterSideB);

        PlaneSeatsAdapter planeSeatsAdapterSideC = new PlaneSeatsAdapter(SeatSelectionActivity.this, seatNumbersC);
        firstClassSideC.setAdapter(planeSeatsAdapterSideC);

        PlaneSeatsAdapter planeSeatsAdapterSideD = new PlaneSeatsAdapter(SeatSelectionActivity.this, seatNumbersD);
        firstClassSideD.setAdapter(planeSeatsAdapterSideD);

        SeatSelectedAdapter seatSelectedAdapter = new SeatSelectedAdapter(SeatSelectionActivity.this, seatNumbersSelected);
        selectedSeatsRecycler.setAdapter(seatSelectedAdapter);

    }
}
