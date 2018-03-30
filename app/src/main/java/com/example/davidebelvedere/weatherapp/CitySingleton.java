package com.example.davidebelvedere.weatherapp;


import java.util.List;

public class CitySingleton {
    private static List<City> CITIES;
    private static CitySingleton mySingleton = new CitySingleton();

    private CitySingleton() {

    }

    public static CitySingleton getInstance() {
        return mySingleton;
    }

    public List<City> getCityArray() {
        return CITIES;
    }

    public void setContactArray(List<City> array) {
        CITIES = array;
    }

    public void addItem(City newContact) {

        CITIES.add(newContact);
    }

    public void removeItem(int position) {
        CITIES.remove(position);
    }
}
