/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cannamiranda.previsaodotempo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author clara
 */
public class GsonHandler {
    public void stringToJson(String data){
        //private static void writeUsingFileWriter(String data) {
        getDaysFromstring(data);
        File file = new File("teste.json");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getPrintableDailyData(DataModel model){
        String str = "Previsão do tempo para os próximos 7 dias\n\n";
        
        for (Daily i : model.daily){
        //  System.out.println(i.toString()); 
            str = str.concat(i.toString());
        }
        
        //System.out.println(str);
        return str;
        
    }
    
    public String getDaysFromstring(String data){
      DataModel model;
      Gson gson = new Gson();
      model = gson.fromJson(data, DataModel.class);
      
      //System.out.println("\nImprimindo previsão do tempo para os próximos 7 dias\n");
      
      String str = getPrintableDailyData(model);
      /*for (Daily i : model.daily){
          System.out.println(i.toString()); 
          str.concat(i.toString());
      }*/
    
      //return model;
     // System.out.println(str);
      return str;
    }
    

    
}
