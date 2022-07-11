package com.example.eisapp.model;

public class PaymentHandler {

    // -Auch als Singleton ? damit FragmentSale und das untere Fragment drauf zugreifen können-

    // Oder als Member von Economy erzeugen + darafu zugreifen. !!!!

    public float currentSum = 0;
    public float given;

    // Summe der current ausgeben lassen
    // Evtl summe mit trinkgeld eingeben
    // Gegebenes eingeben + prüfen
    // Rückgeld eingeben

    //Bei tip: onEnter des Summenfelds setzt die Currensum anders!
    public void pay(){
        Economy.getInstance().dailyIncome += currentSum;
        Economy.getInstance().finishCurrentSale();

    }

    //Wenn tip gegeben wurde (on enter vom summenfeld im controller triggert boolean dort!!)


    //Gibt rückgeld, egal ob
    public float getChange(float paidCash){

        return paidCash-currentSum;
    }

}
