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
 *  Essa classe é o modelo de dados pra montar o objeto extraido do json
 */


public class DataModel {
    String cityName;
    double lat;
    double lon;
    String timezone;
    String timezone_offset;
    ArrayList<Daily> daily = new ArrayList<>();  //vetor com a previsão do tempo pros próximos dias, é daqui que eu pego as infos
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
    
    
    //sobreescrita do método toString pra printar bonitinho meus dados
    @Override
    public String toString() { 
        
        String date = dateFormater(this.dt); //chama o método de conversao do tempo
        
        return String.format("\nNo dia " + date + " o clima estará " + this.weather.get(0).description
        + "\n\tMinima: " + this.temp.min + "°C \n\tMaxima: "+ this.temp.max + "°C\n"); 
    }
    
    //converte o formato de data de UTC pra um humanamente legivel
    public String dateFormater(long date){
        DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy");  //pattern da data
        
        long unixTime = date;
        final String formattedDtm = Instant.ofEpochSecond(unixTime)
                .atZone(ZoneId.of("GMT-3"))   //nesse parametro que muda o fusohorário, por padrão é o horário de sp
                .format(formatter);

        return(formattedDtm); 
    }
}