package com.example.davidebelvedere.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.w3c.dom.Text;

public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        Intent intent = getIntent();
        String nameFromIntent = intent.getStringExtra("Nome");
        TextView name = findViewById(R.id.name);
        name.setText(nameFromIntent);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + nameFromIntent.toLowerCase() + "&appid=2439d518e81cee90fd7a61cfe1109dd4";


        GsonRequest jsonObjectReq = new GsonRequest(url, City.class, null,
                new Response.Listener<City>() {
                    @Override
                    public void onResponse(City response) {

                        String weather = response.getWeather().get(0).getDescription();
                        double temperature = response.getMain().getTemp();
                        TextView weatherView = (TextView) findViewById(R.id.weather);
                        TextView temperatureView = (TextView) findViewById(R.id.temperature);
                        weatherView.setText(weather);
                        temperatureView.setText("" + ((int)(temperature - 273)));

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ServiceQueueSingleton.getInstance(this).addToRequestQueue(jsonObjectReq);
    }

}
