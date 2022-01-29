package com.devbramm.hireplane.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devbramm.hireplane.AirportDepatureSearchActivity;
import com.devbramm.hireplane.Common.CommonUtils;
import com.devbramm.hireplane.FlightsActivity;
import com.devbramm.hireplane.R;

import java.util.ArrayList;

public class DepartureCityAdapter extends RecyclerView.Adapter<DepartureCityAdapter.SearchViewHolder> {

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

    public DepartureCityAdapter(Context context, ArrayList<String> cityNameList, ArrayList<String> cityIATACodeList, ArrayList<String> airportNameList) {
        this.context = context;
        this.cityNameList = cityNameList;
        this.cityIATACodeList = cityIATACodeList;
        this.airportNameList = airportNameList;
    }

    @NonNull
    @Override
    public DepartureCityAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.departure_airport_search_item, parent, false);
        return new DepartureCityAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartureCityAdapter.SearchViewHolder holder, final int position) {
        holder.deptCityNameandCode.setText(cityNameList.get(position));
        holder.deptAirportName.setText(cityIATACodeList.get(position));
        holder.deptCityNameandCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.flightDepatureAirport = cityNameList.get(position);
                CommonUtils.flightDepatureAirportCode = cityIATACodeList.get(position);
                //FlightsActivity.recyclerView.setVisibility(View.GONE);
                FlightsActivity.originAirportName.setText("(" + cityIATACodeList.get(position) + ") " + cityNameList.get(position));
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityNameList.size();
    }


}
