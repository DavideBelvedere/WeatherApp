package com.example.davidebelvedere.weatherapp;

import android.content.Context;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by davidebelvedere on 15/02/18.
 */

public class Utility {

    public static void initDataSource(Context context) {
        List<City> cityList = new ArrayList();
        cityList.add(new City("Milano"));
        cityList.add(new City("Roma"));
        cityList.add(new City("Parigi"));
        cityList.add(new City("New York"));
        cityList.add(new City("Mumbay"));
        CitySingleton.getInstance().setContactArray(cityList);
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



}
