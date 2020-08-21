/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cannamiranda.previsaodotempo;

/**
 *
 * @author clara
 */

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpGet {

    OkHttpClient client = new OkHttpClient();
    //todo colocar url como atributo
    static OWMRequests owm;
    String responseJson;
    GsonHandler jsonHandler = new GsonHandler();
    
    //construtor
    public OkHttpGet() {
        this.owm = new OWMRequests();
        
    }
    
    public String requestForecastJson(String lat, String lon){
        String url = "https://api.openweathermap.org/data/2.5/onecall?lat=" 
                + lat +"&lon=" + lon + "&exclude=current,minutely,hourly" 
                + "&units=metric&lang=pt_br"    
                + "&appid=" + this.owm.getAPI_KEY();
        //System.out.println(url);
        
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String data = response.body().string();
            //jsonHandler.stringToJson(data);
            jsonHandler.getDaysFromstring(data);
            return data;
        } catch (IOException e){
            System.out.print(e.getMessage());
            return e.getMessage();
        }
    }
}