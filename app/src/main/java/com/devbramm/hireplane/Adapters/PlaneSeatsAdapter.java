package com.devbramm.hireplane.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.devbramm.hireplane.R;
import com.devbramm.hireplane.SeatSelectionActivity;

import java.util.ArrayList;

public class PlaneSeatsAdapter extends RecyclerView.Adapter<PlaneSeatsAdapter.ViewHolder> {

    //vars
    private Context mContext;
    private ArrayList<String> mSeatNumber = new ArrayList<>();

    public PlaneSeatsAdapter(Context Context, ArrayList<String> seatNumber) {
        mContext = Context;
        mSeatNumber = seatNumber;
    }

    @NonNull
    @Override
    public PlaneSeatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_class_seat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlaneSeatsAdapter.ViewHolder holder, final int position) {
        Glide.with(mContext).load(R.drawable.first_class_seat_unselected).into(holder.seatImage);

        holder.seatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Seat: " + mSeatNumber.get(position), Toast.LENGTH_SHORT).show();
                Glide.with(mContext).load(R.drawable.first_class_seat_book_now).into(holder.seatImage);
                SeatSelectionActivity.seatNumbersSelected.add(mSeatNumber.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSeatNumber.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView seatImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            seatImage = itemView.findViewById(R.id.singlee_seat_item);
        }
    }
}
