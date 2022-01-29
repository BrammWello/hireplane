package com.devbramm.hireplane.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devbramm.hireplane.AirportDepatureSearchActivity;
import com.devbramm.hireplane.Common.CommonUtils;
import com.devbramm.hireplane.FlightSearchActivity;
import com.devbramm.hireplane.FlightsActivity;
import com.devbramm.hireplane.R;

import java.util.ArrayList;

public class DestinationCityAdapter extends RecyclerView.Adapter<DestinationCityAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> cityNameList;
    ArrayList<String> cityIATACodeList;
    ArrayList<String> airportNameList;

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView deptCityNameandCode,deptAirportName;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            deptCityNameandCode = itemView.findViewById(R.id.dept_city_name);
            deptAirportName = itemView.findViewById(R.id.dept_airport_name);
        }
    }

    public DestinationCityAdapter(Context context, ArrayList<String> cityNameList, ArrayList<String> cityIATACodeList, ArrayList<String> airportNameList) {
        this.context = context;
        this.cityNameList = cityNameList;
        this.cityIATACodeList = cityIATACodeList;
        this.airportNameList = airportNameList;
    }

    @NonNull
    @Override
    public DestinationCityAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.departure_airport_search_item, parent, false);
        return new DestinationCityAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationCityAdapter.SearchViewHolder holder, final int position) {
        holder.deptCityNameandCode.setText(cityNameList.get(position));
        holder.deptAirportName.setText(cityIATACodeList.get(position));
        holder.deptCityNameandCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.flightDestinationAirport = cityNameList.get(position);
                CommonUtils.flightDestinationAirportCode = cityIATACodeList.get(position);
                //FlightsActivity.recyclerView.setVisibility(View.GONE);
                FlightsActivity.destinationAirportName.setText("(" + cityIATACodeList.get(position) + ") " + cityNameList.get(position));
                ((Activity) context).finish();
                ((Activity) context).startActivity(new Intent(((Activity) context), FlightSearchActivity.class));
                ((Activity) context).overridePendingTransition(R.anim.fade_in_resume_activity,R.anim.fade_out_move_down_activity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityNameList.size();
    }


}