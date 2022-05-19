package com.example.eisapp.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Enumeration;

public class Economy {

   HashMap<Eis, Integer> dailySoldIce;
   LinkedHashMap<Eis, Integer> currentSoldIce;


   double dailyIncome;
   //Optional: Einkommen/Verkaufte eis pro Film

   public Economy(Eis[] eise){
      //TODO: hol die eisliste aus dem Markenmanager
      dailySoldIce = new LinkedHashMap<Eis,Integer>();
      currentSoldIce = new LinkedHashMap<Eis, Integer>();

     // Sicher, dass alle mit 0 initilaisiert werden sollen ? Das macht das resetten komplexer
      for(Eis eis : eise){
       dailySoldIce.put(eis,0);

    }

   }
  //TODO: Remove aus der current liste

   public float getSum(LinkedHashMap<Eis, Integer> list) {

      return 0;
   }

   // zählt gedrücktes eis zu current dazu
   public void addSoldIce(Eis eis) {

      if(currentSoldIce.containsKey(eis)){
         currentSoldIce.put(eis, currentSoldIce.get(eis)+1);
      return;
      }
      currentSoldIce.put(eis, 1);

   }



   // zählt currentsoldice zu daily dazu
   public void finishCurrentSale() {

      currentSoldIce.forEach((key,value) ->

              // Da alle mit 0 initialisiert: kein problem! Einfach die verkauften werte adden
              dailySoldIce.put(key,dailySoldIce.get(key)+value)
      );


      currentSoldIce.clear();
   }


   public void printDay(){
      System.out.println("DAILY SALES:");
      dailySoldIce.forEach((key, value) -> System.out.println(key.name + ":" + value));
   }

   public void printCurr() {
      System.out.println("Current sales:");
      currentSoldIce.forEach((key, value) -> System.out.println(key.name + ":" + value));
   }
}