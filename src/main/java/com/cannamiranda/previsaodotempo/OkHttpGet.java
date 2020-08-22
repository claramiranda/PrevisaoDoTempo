/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cannamiranda.previsaodotempo;

/**
 *
 * @author clara
 * Essa classe é o handler do meu request GET ao servidor
 * 
 * Preciso dessa classe pq o unico jeito de conseguir a previsão do tempo gratuitamente é fazer um request get pro servidor
 * enviando a lat, a lon e mais alguns parametros, e depois tratar o JSON de response
 * 
 * Eu pego os dados de localização e demais informações da cidade 
 * 
 */

import java.io.IOException;
import net.aksingh.owmjapis.model.param.City;
import net.aksingh.owmjapis.model.param.Coord;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpGet {

    OkHttpClient client = new OkHttpClient();
    static OWMRequests owm; //esse atributo é o objeto de acesso à api OWMJ (open weather maps implementada pra consumo em java)
    
    GsonHandler jsonHandler = new GsonHandler();  //handler do gson pra chamar os métodos da classe
    
    //construtor
    public OkHttpGet() {
        this.owm = new OWMRequests();
        
    }
    
    public String requestForecastJson(String lat, String lon, String cityName){
        
        String url = "https://api.openweathermap.org/data/2.5/onecall?lat="     //montagem da url
                + lat +"&lon=" + lon + "&exclude=current,minutely,hourly" 
                + "&units=metric&lang=pt_br"    
                + "&appid=" + this.owm.getAPI_KEY();
        
        String jsonContent;
        
        try {
            //fazendo o request
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            
            String data = response.body().string();     //lê json
            
            jsonContent = jsonHandler.getDaysFromstring(data, cityName); // Chama o metodo que vai fazer o tratamento do texto
            return jsonContent;
        } 
        catch (IOException e){ 
        //Well, essa parte não tá completa ainda... preciso dar uma revisada na parte de GUI pra implementar melhor
          String errorCode = e.getCause().toString();
          String errorMessage = "Entre em contato com o desenvolvedor. Error code: HTTP_GET_ERROR" + errorCode;
          
          //JFrameHandler handler = new JFrameHandler(errorMessage);  //certo, acho que não é assim que eu mando a msg de erro pra GUI
          
          //Pelo menos um print diferente pra eu saber que o erro foi aqui
          System.out.print("\nErro no request HTTP\n"
                            + "Código do Erro " 
                            + errorCode + "\n"
                            + "Entre em contato com o desenvolvedor e informe o código de erro.");
            return errorMessage;
        }
    }
    
    //Método chamado pela interface gráfica pra fazer as chamadas de request pra api e pro site
    public String getDataFromCity(String cityName){
        
        City city = OkHttpGet.owm.getCityByCityName(cityName); // Solicita os dados da cidade pra API
        Coord coord = city.getCoordData();  //Filtra somente os dados necessários pra fazer o request HTTP
        
        //Separa coordenadas pra mandar via metodo GET
        String lat = coord.getLatitude().toString();
        String lon = coord.getLongitude().toString();
        
        String data = requestForecastJson(lat,lon,cityName); //chama metodo que acessa 
        
        return data;
    }
}