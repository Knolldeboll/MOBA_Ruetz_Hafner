package com.example.eisapp.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Economy {

   LinkedHashMap<Eis, Integer> dailySoldIce;
   LinkedHashMap<Eis, Integer> currentSoldIce;
   PaymentHandler paymentHandler;
   public static Economy Instance;


   double dailyIncome;


   public static Economy getInstance(){
      if(Instance == null){
         Eis eins = new Eis("eins", 1.5f);
         Eis zwei = new Eis("zwei",1.5f);
         Eis drei = new Eis("dreoi",1.5f);
         Instance = new Economy(new Eis[]{eins, zwei, drei} );

      }
      return Instance;

   }

   //Optional: Einkommen/Verkaufte eis pro Film

   //TODO: Konstruktor so abändern, dass der sich direkt die verfügbaren eise ausm markenmanager zieht
   public Economy(Eis[] eise){


      paymentHandler = new PaymentHandler();
      //LinkedHashmap hat den vorteil der stabilen reihenfolge
      // (Last added last)
      dailySoldIce = new LinkedHashMap<Eis,Integer>();
      currentSoldIce = new LinkedHashMap<Eis, Integer>();


     // Sicher, dass alle mit 0 initilaisiert werden sollen ? Das macht das resetten komplexer
      // bei der dailyliste egal!
      for(Eis eis : eise){
       dailySoldIce.put(eis,0);

    }

   }

   // Bei Tagesende wird getsum von dailysales berechnet. Differenz zum gesamtertrag gibt tip
   private float getSum(LinkedHashMap<Eis, Integer> list) {

      float val = 0;

      // Hat ja auch gar nicht lange gedauert die
      // scheiße
      for(Map.Entry<Eis,Integer> ent : list.entrySet()){
         val += ent.getKey().preis * ent.getValue();

      }

      return val;
   }

   // Remove aus current liste
   // kopplung an "-" Button
   public void removeSoldIce(Eis eis){
      if(currentSoldIce.containsKey(eis)) {
         currentSoldIce.put(eis, currentSoldIce.get(eis) - 1);
      }
      return;
   }

   // zählt gedrücktes eis zu current dazu
   //Kopplung an eisbutton
   public void addSoldIce(Eis eis) {

      if(currentSoldIce.containsKey(eis)){
         currentSoldIce.put(eis, currentSoldIce.get(eis)+1);
      return;
      }
      currentSoldIce.put(eis, 1);

   }

   // View holt sich dies
   public float getCurrentValue(){
      return getSum(currentSoldIce);

   }


   // zählt currentsoldice zu daily dazu
   // Wert addition kommt vom paymentmanager
   public void finishCurrentSale() {

      currentSoldIce.forEach((key,value) ->

              // Da alle mit 0 initialisiert: kein problem! Einfach die verkauften werte adden
              dailySoldIce.put(key,dailySoldIce.get(key)+value)
      );

      // Wie geht bezahlen ?

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