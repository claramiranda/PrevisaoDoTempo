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

/**
 *
 * @author clara
 * Essa classe faz a manipulação da API OWM
 * A API não traz nenhum resultado de previsão do tempo pq o servidor não dá essas informações na versão gratuita
 * Mas eu consigo pedir pro servidor através de requst GET um json com a previsao do tempo pros próximos 7 dias
 * pra fazeer esse request eu preciso da latitude e longitude da cidade, e é isso que eu vou buscar com os métodos dessa classe
 */
public class OWMRequests {
   static private String API_KEY = "0fdcc8a42265e1af6e07ec19f8e7d0c7";  //eu sei, isso não é seguro... mas serve pra usar em dev
   OWM owm;     //objeto principal de manipulação da API

   //Construtor inicializando com alguns parametros uteis
    public OWMRequests() { 
        owm = new OWM(API_KEY);
        owm.setUnit(OWM.Unit.METRIC); //seta o sistema métrico pra recer temperatura em Celsium
        owm.setLanguage(OWM.Language.PORTUGUESE); //seta linguagem pra pt_br
    }
   
    //Não é o jeito mais seguro de pedir a key, mas pelo menos ta encapsulado
    public static String getAPI_KEY() {
        return API_KEY;
    }
    
    //Verifica se a cidade é existe
    public boolean checkIfCityExists(String cityName){
        boolean check;
        HourlyWeatherForecast hwf = new HourlyWeatherForecast();
       try {
           hwf = owm.hourlyWeatherForecastByCityName(cityName);
       } catch (APIException ex) {
           Logger.getLogger(OWMRequests.class.getName()).log(Level.SEVERE, null, ex);
       }
        check = hwf.hasCityData();
        return check;
    }
     
    //Método que recebe nome da cidade e retorna as informações que a api tem do local
    public City getCityByCityName(String cityName) {
        City city = new City();
        
        try {
            //OWM owm = new OWM(API_KEY);
            
            //Dava pra resolver o problema aqui, dando a previsão do tempo da cidade pra próxima hora
            //mas sinceramente, qual a utilidade de uma previsão de uma hora?
            //no entando vou usar esse objeto pra usar os metodos dele pra pegar as infos da cidade
            HourlyWeatherForecast hwf = owm.hourlyWeatherForecastByCityName(cityName);
            
           //boolean hasCityData = hwf.hasCityData();
           // hasCityData ? : throw APIException;   //implementar esse condicional aqui pra ver se tem infos
            city = hwf.getCityData();      
            
        } catch (APIException e) {
            //todo tratamento erros api
            //Logger.getLogger(OWMRequests.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Erro no request a api");
            
            String errorCode = e.getCause().toString();
            String errorMessage = "Entre em contato com o desenvolvedor. Error code: API_error" + errorCode;
            
            //TODO imprimir error na GUI
            //JFrameHandler handler = new JFrameHandler(errorMessage);
          
          System.out.print("\nErro no request API\n"
                            + "Código do Erro " 
                            + errorCode + "\n"
                            + "Entre em contato com o desenvolvedor e informe o código de erro.");
        }
        
        return city;
    }
    
}
