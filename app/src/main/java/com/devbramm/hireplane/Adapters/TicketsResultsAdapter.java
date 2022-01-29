package com.devbramm.hireplane.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.devbramm.hireplane.Common.CommonUtils;
import com.devbramm.hireplane.Models.AirlineGetter;
import com.devbramm.hireplane.R;

public class TicketsResultsAdapter extends RecyclerView.Adapter<TicketsResultsAdapter.SearchViewHolder>{

    Context context;
    AirlineGetter airlineGetter;

    public TicketsResultsAdapter(Context context, AirlineGetter airlineGetter) {
        this.context = context;
        this.airlineGetter = airlineGetter;
    }

    @NonNull
    @Override
    public TicketsResultsAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flight_tickets_search_item, parent, false);
        return new TicketsResultsAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsResultsAdapter.SearchViewHolder holder, int position) {
        holder.ticketDeptAirport.setText(CommonUtils.flightDepatureAirportCode);
        holder.ticketDestAirport.setText(CommonUtils.flightDestinationAirportCode);
        holder.airlineItemName.setText(airlineGetter.getAirline_name());
        holder.airlineItemCode.setText(airlineGetter.getAirline_code());
        Glide.with(context).load(airlineGetter.getAirline_logo()).into(holder.airlineItemLogo);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView airlineItemCode, airlineItemName,ticketDeptAirport, ticketDestAirport;
        ImageView airlineItemLogo;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            airlineItemCode = itemView.findViewById(R.id.airline_item_code);
            airlineItemName = itemView.findViewById(R.id.airline_item_name);
            ticketDeptAirport = itemView.findViewById(R.id.ticket_dept_airport);
            ticketDestAirport = itemView.findViewById(R.id.ticket_dest_airport);
            airlineItemLogo = itemView.findViewById(R.id.airline_item_logo);
        }
    }
}
