package com.example.davidebelvedere.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;


/**
 * Created by davidebelvedere on 15/02/18.
 */

public class Utility {
    public final static int REQUEST_CODE = 10;

    public static void initDataSource(Context context) {
        List<City> cityList = new ArrayList();
        cityList.add(new City("Current Position"));
        cityList.add(new City("Milano"));
        cityList.add(new City("Roma"));
        cityList.add(new City("Parigi"));
        cityList.add(new City("New York"));
        cityList.add(new City("Berlino"));
        CitySingleton.getInstance().setCityArray(cityList);
    }


    public static List<City> getDataSourceItemList() {
        return CitySingleton.getInstance().getCityArray();
    }

    public static void addItem(City contatto) {
        CitySingleton.getInstance().addItem(contatto);
    }

    public static void removeItem(int pos) {
        CitySingleton.getInstance().removeItem(pos);
    }

    public static int getCount() {
        return CitySingleton.getInstance().getCityArray().size();
    }

    public static String getCurrentCityName(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        //location manager will take the best location from the criteria
        locationManager.getBestProvider(criteria, true);
        @SuppressLint("MissingPermission")
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));

        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        String cityName = "";
        try {
            addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {
                cityName = addresses.get(0).getLocality().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityName;
    }

}
