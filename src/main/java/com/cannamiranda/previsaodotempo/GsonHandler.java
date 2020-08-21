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
    
    File file;

    public GsonHandler() {
        this.file = new File("teste.json");
    }
    
    
  /*  public void getDailyInformationFromJson(){
        String ss;
        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(ss, Map.class); // parse
        JsonObject jsonTree = (JsonObject) gson.toJsonTree(data);
        String dailyJson = jsonTree.get("daily").toString();
    }*/
    
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
    
    public void getDaysFromstring(String data){
      DataModel model;
      Gson gson = new Gson();
      model = gson.fromJson(data, DataModel.class);
      
      System.out.println("\nImprimindo previsão do tempo para os próximos 7 dias\n");
      
      for (Daily i : model.daily){
          System.out.println(i.toString());
          
      }
        
    }
    
}
