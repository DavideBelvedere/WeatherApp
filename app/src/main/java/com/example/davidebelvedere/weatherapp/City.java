package com.example.davidebelvedere.weatherapp;

import java.util.List;

public class City {
    private String nome;
    private List<WeatherType> weather;
    private TemperatureType main;


    public List<WeatherType> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherType> weather) {
        this.weather = weather;
    }

    public TemperatureType getMain() {
        return main;
    }

    public void setMain(TemperatureType main) {
        this.main = main;
    }


    public City(String nome) {
        nome = nome.toUpperCase();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
