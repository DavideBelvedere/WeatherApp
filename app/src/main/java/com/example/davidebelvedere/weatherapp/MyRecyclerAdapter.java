package com.example.davidebelvedere.weatherapp;


import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by davidebelvedere on 15/02/18.
 */


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<City> values;


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        String settedName;
        int position;
        Context context;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.city_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("Nome", settedName);
            context.startActivity(intent);

        }
    }

    public MyRecyclerAdapter(List<City> values, Context context) {
        this.values = values;
        this.context = context;
    }

    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, int position) {

        City currentCity = values.get(position);
        holder.name.setText(currentCity.getNome());
        holder.settedName = currentCity.getNome();
        holder.position = position;
        holder.context = context;

    }

    public void updateData() {
        this.values = CitySingleton.getInstance().getCityArray();
        notifyDataSetChanged();
    }
}

