package com.example.davidebelvedere.weatherapp;


import android.content.Context;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by davidebelvedere on 15/02/18.
 */


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<City> values;


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        int position;
        Context context;
        TextView weatherView;
        TextView hour;
        TextView date;
        ImageView img;
        TextView temp_max;
        TextView temp_min;
        LinearLayout layout;


        public MyViewHolder(View view) {
            super(view);
            layout = (LinearLayout) view.findViewById(R.id.background);
            weatherView = (TextView) view.findViewById(R.id.city_weather);
            name = (TextView) view.findViewById(R.id.city_name);
            hour = (TextView) view.findViewById(R.id.city_hour);
            date = (TextView) view.findViewById(R.id.city_date);
            img = (ImageView) view.findViewById(R.id.imgWeather);
            temp_max = (TextView) view.findViewById(R.id.city_temp_max);
            temp_min = (TextView) view.findViewById(R.id.city_temp_min);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            /*Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("Nome", settedName);
            context.startActivity(intent);*/

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
    public void onBindViewHolder(final MyRecyclerAdapter.MyViewHolder holder, int position) {

        final MyRecyclerAdapter.MyViewHolder utilityHolder = holder;
        City currentCity = values.get(position);
        holder.name.setText(currentCity.getNome());

        holder.position = position;
        holder.context = context;
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM");
        SimpleDateFormat tf = new SimpleDateFormat(("HH:mm"));
        String formattedDate = df.format(c);
        String formattedTime = tf.format(c);
        holder.date.setText(formattedDate);
        holder.hour.setText(formattedTime);

        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + currentCity.getNome().toLowerCase() + "&appid=2439d518e81cee90fd7a61cfe1109dd4";


        GsonRequest jsonObjectReq = new GsonRequest(url, City.class, null,
                new Response.Listener<City>() {
                    @Override
                    public void onResponse(City response) {

                        String weather = response.getWeather().get(0).getDescription();
                        double min = response.getMain().getTemp_min();
                        double max = response.getMain().getTemp_max();
                        utilityHolder.weatherView.setText(weather);
                        utilityHolder.temp_max.setText("" + ((int) (max - 273)));
                        utilityHolder.temp_min.setText("" + ((int) (min - 273)));
                        switch (response.getWeather().get(0).getIcon().substring(0, 2)) {
                            case "01":
                                utilityHolder.img.setBackgroundResource(R.drawable.ic_01d);
                                utilityHolder.layout.setBackgroundResource(R.color.orange);
                                break;
                            case "02":
                                utilityHolder.img.setBackgroundResource(R.drawable.ic_02d);
                                utilityHolder.layout.setBackgroundResource(R.color.orange);
                                break;
                            case "03":
                                utilityHolder.img.setBackgroundResource(R.drawable.ic_03d);
                                utilityHolder.layout.setBackgroundResource(R.color.gray);
                                break;
                            case "04":
                                utilityHolder.img.setBackgroundResource(R.drawable.ic_04d);
                                utilityHolder.layout.setBackgroundResource(R.color.gray);
                                break;
                            case "09":
                                utilityHolder.img.setBackgroundResource(R.drawable.ic_09d);
                                utilityHolder.layout.setBackgroundResource(R.color.blue);
                                break;
                            case "10":
                                utilityHolder.img.setBackgroundResource(R.drawable.ic_10d);
                                utilityHolder.layout.setBackgroundResource(R.color.blue);
                                break;
                            case "11":
                                utilityHolder.img.setBackgroundResource(R.drawable.ic_11d);
                                utilityHolder.layout.setBackgroundResource(R.color.blue);
                                break;
                            case "13":
                                utilityHolder.img.setBackgroundResource(R.drawable.ic_13d);
                                utilityHolder.layout.setBackgroundResource(R.color.blue);
                                break;
                            case "50":
                                utilityHolder.img.setBackgroundResource(R.drawable.ic_50d);
                                utilityHolder.layout.setBackgroundResource(R.color.gray);
                                break;
                            default:
                                break;
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ServiceQueueSingleton.getInstance(context).addToRequestQueue(jsonObjectReq);

    }


    public void updateData() {
        this.values = CitySingleton.getInstance().getCityArray();
        notifyDataSetChanged();
    }

}

