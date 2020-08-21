/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cannamiranda.previsaodotempo;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.City;
import net.aksingh.owmjapis.model.param.Coord;

/**
 *
 * @author clara
 */
public class OWMRequests {
   static private String API_KEY = "0fdcc8a42265e1af6e07ec19f8e7d0c7";
   OWM owm;

    public OWMRequests() {
        owm = new OWM(API_KEY);
        owm.setUnit(OWM.Unit.METRIC);
        owm.setLanguage(OWM.Language.PORTUGUESE);
    }
   
   

    public static String getAPI_KEY() {
        return API_KEY;
    }
    
    
    public int getCityIdByCityName(String cityName) {
        
        int cityId=0;
        
        try {
            
            //um tipo de previsão é essa, de uma hora... mas agora foda-c vou fazer via json
            HourlyWeatherForecast hwf = owm.hourlyWeatherForecastByCityName(cityName);
            
            //verificar aqui com o hascitydata e já lançar a excessão se der ruim
            
            City city = hwf.getCityData();
            Coord coord = city.getCoordData();
            cityId = city.getId();
            
            //System.out.println(hwf.toString());
            
        } catch (APIException ex) {
            //todo tratamento erros api
            Logger.getLogger(OWMRequests.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro no request a api");
        }
        
        return cityId;
    }
    
        public City getCityByCityName(String cityName) {
        
        City city = new City();
        
        try {
            OWM owm = new OWM(API_KEY);
            
            //um tipo de previsão é essa, de uma hora... mas agora foda-c vou fazer via json
            HourlyWeatherForecast hwf = owm.hourlyWeatherForecastByCityName(cityName);
            
            //verificar aqui com o hascitydata e já lançar a excessão se der ruim
            
            city = hwf.getCityData();      
            //System.out.println(hwf.toString());
            
        } catch (APIException ex) {
            //todo tratamento erros api
            Logger.getLogger(OWMRequests.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro no request a api");
        }
        
        return city;
    }
    
}
