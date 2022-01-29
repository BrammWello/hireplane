package com.devbramm.hireplane.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devbramm.hireplane.Common.CommonUtils;
import com.devbramm.hireplane.R;

import java.util.ArrayList;

public class SeatSelectedAdapter extends RecyclerView.Adapter<SeatSelectedAdapter.ViewHolder> {

    //vars
    private Context mContext;
    private ArrayList<String> mSeatNumberSelected = new ArrayList<>();

    public SeatSelectedAdapter(Context Context, ArrayList<String> seatNumberSelected) {
        mContext = Context;
        mSeatNumberSelected = seatNumberSelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_selected_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.seatClass.setText(CommonUtils.flightClassSelected);
        holder.seatNumber.setText(mSeatNumberSelected.get(position));
    }

    @Override
    public int getItemCount() {
        return mSeatNumberSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView seatClass, seatNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            seatClass = itemView.findViewById(R.id.seat_class);
            seatNumber = itemView.findViewById(R.id.seat_number);
        }
    }
}
