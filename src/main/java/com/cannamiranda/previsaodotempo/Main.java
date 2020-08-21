/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cannamiranda.previsaodotempo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.aksingh.owmjapis.model.param.City;
import net.aksingh.owmjapis.model.param.Coord;

/**
 *
 * @author clara
 */
public class Main {
    
        public static void main(String[] args) throws IOException {
            System.out.println("\nPrevisão do Tempo\n"); 
            System.out.println("Insira a cidade: ");
            
            String cityName;
            BufferedReader  scanner = new BufferedReader(new InputStreamReader(System.in));
            cityName = scanner.readLine();
            
            OkHttpGet requestGet = new OkHttpGet();
            int cityId = OkHttpGet.owm.getCityIdByCityName(cityName);
       
            //System.out.println( "Cidade: " + cityName + "\nId: " +  cityId);
                    
            City city = OkHttpGet.owm.getCityByCityName(cityName);
            Coord coord = city.getCoordData();
            //System.out.print("Coordenadas" + coord.toString());
            
            String lat = coord.getLatitude().toString();
            String lon = coord.getLongitude().toString();
                    
            requestGet.requestForecastJson(lat,lon);
    }
}
