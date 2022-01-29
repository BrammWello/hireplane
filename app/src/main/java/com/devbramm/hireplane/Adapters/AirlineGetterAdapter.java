package com.devbramm.hireplane.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devbramm.hireplane.Helpers.TicketItemClickListener;
import com.devbramm.hireplane.R;

import java.util.ArrayList;

public class AirlineGetterAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    //vars
    public TextView airlineItemCode, airlineItemName,ticketDeptAirport, ticketDestAirport, leavingTimeTicket, arrivalTimeTicket, ticketPrice, timeBetweenTravel;
    public ImageView airlineItemLogo;

    private TicketItemClickListener ticketItemClickListener;

    public AirlineGetterAdapter(@NonNull View itemView) {
        super(itemView);

        airlineItemCode = itemView.findViewById(R.id.airline_item_code);
        airlineItemName = itemView.findViewById(R.id.airline_item_name);
        ticketDeptAirport = itemView.findViewById(R.id.ticket_dept_airport);
        ticketDestAirport = itemView.findViewById(R.id.ticket_dest_airport);
        airlineItemLogo = itemView.findViewById(R.id.airline_item_logo);
        leavingTimeTicket = itemView.findViewById(R.id.leaving_time_ticket);
        arrivalTimeTicket = itemView.findViewById(R.id.arrival_time_ticket);
        ticketPrice = itemView.findViewById(R.id.ticket_price_item);
        timeBetweenTravel = itemView.findViewById(R.id.time_between_item);

        itemView.setOnClickListener(this);
    }

    public void setTicketItemClickListener(TicketItemClickListener ticketItemClickListener) {
        this.ticketItemClickListener = ticketItemClickListener;
    }

    @Override
    public void onClick(View v) {
        ticketItemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
