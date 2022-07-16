package com.example.eisapp.model;


// Veraltet!
public class PaymentHandler {

    // -Auch als Singleton ? damit FragmentSale und das untere Fragment drauf zugreifen können-

    // Oder als Member von Economy erzeugen + darafu zugreifen. !!!!




    // Summe der current ausgeben lassen
    // Evtl summe mit trinkgeld eingeben
    // Gegebenes eingeben + prüfen
    // Rückgeld eingeben

    //Bei tip: onEnter des Summenfelds setzt die Currensum anders!
    public void pay(float given) {



        // TODO: Passt gegebenes geld ?



        Economy.getInstance().dailyIncome += given;
        Economy.getInstance().finishCurrentSale(); // dailySum +=

    }

    //Wenn tip gegeben wurde (on enter vom summenfeld im controller triggert boolean dort!!)


    // Gibt rückgeld, egal ob
    //  TODO: Bei eisklick: currentSum ++;

    public float getChange(float given, float total) {

        return given -  total;
    }

}
