/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cannamiranda.previsaodotempo;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author clara
 */


public class DataModel {
    double lat;
    double lon;
    String timezone;
    String timezone_offset;
    ArrayList<Daily> daily = new ArrayList<>();
}

class Temp {
    String morn;
    String day;
    String eve;
    String night;
    String min;
    String max;
}

class Feels_like {
    String mor;
    String day;
    String eve;
    String night;
}

class Weather {
    String id;
    String main;
    String description;
}

class Daily {
    long dt;
    String sunrise;
    String sunset;
    Temp temp;
    Feels_like Feels_like;
    String pressure;
    String humidity;
    String dew_point;
    String wind_speed;
    String wind_gust;
    String wind_deg;
    //Weather weather;
    ArrayList<Weather> weather = new ArrayList<>();
    String clouds;
    String uvi;
    String pop;
    String rain;
    String snow;
    
    @Override
    public String toString() { 
        String date = dateFormater(this.dt);
        return String.format("Na data " + date + " o clima estará " + this.weather.get(0).description); 
                
    }
    
    public String dateFormater(long date){
        DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long unixTime = date;
        final String formattedDtm = Instant.ofEpochSecond(unixTime)
                .atZone(ZoneId.of("GMT-3"))
                .format(formatter);

        return(formattedDtm); 
    }
}