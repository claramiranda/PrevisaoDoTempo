/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cannamiranda.previsaodotempo;

import com.google.gson.Gson;



/**
 *
 * @author clara
 * Essa classe trata o JSON, ela basicamente faz o parse pra objeto e formata a saida que vai pra GUI
 * 
 */
public class GsonHandler {
    //Metodo que cria a string que vai ser exibida na gui
    public String getPrintableDailyData(DataModel model){
        //cabecalho
        String output = "Previsão do tempo para os próximos 7 dias\nCidade: " + model.cityName;
        
        //loop pra pegar os dados de todos os dias
        for (Daily i : model.daily){
            output = output.concat(i.toString());
        }
        return output;
    }
    
    //Faz o parse do JSON pra DataModel 
    public String getDaysFromstring(String data, String cityName){
        
      DataModel model;
      Gson gson = new Gson();
      
      model = gson.fromJson(data, DataModel.class);
      model.cityName = cityName;
      
      String output = getPrintableDailyData(model);
      return output;
    }
   
}
