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


    public static Economy getInstance() {
        if (Instance == null) {

            Instance = new Economy();

        }
        return Instance;

    }

    //Optional: Einkommen/Verkaufte eis pro Film


    public Economy() {
        MarkenManager mm = MarkenManager.getInstance(null);

        paymentHandler = new PaymentHandler();
        //LinkedHashmap hat den vorteil der stabilen reihenfolge
        // (Last added last)
        dailySoldIce = new LinkedHashMap<Eis, Integer>();
        currentSoldIce = new LinkedHashMap<Eis, Integer>();
    }

    // Bei Tagesende wird getsum von dailysales berechnet. Differenz zum gesamtertrag gibt tip
    private float getSum(LinkedHashMap<Eis, Integer> list) {

        float val = 0;

        // Hat ja auch gar nicht lange gedauert die
        // scheiße
        for (Map.Entry<Eis, Integer> ent : list.entrySet()) {
            val += ent.getKey().preis * ent.getValue();

        }

        return val;
    }

    // Remove aus current liste
    // kopplung an "-" Button
    public void removeSoldIce(Eis eis) {
        if (currentSoldIce.containsKey(eis)) {
            currentSoldIce.put(eis, currentSoldIce.get(eis) - 1);
            if (currentSoldIce.get(eis) == 0) {
                System.out.println("0 Stück übrig! Entferne");
                currentSoldIce.remove(eis);
            }

        }
        return;
    }

    // zählt gedrücktes eis zu current dazu
    //Kopplung an eisbutton
    public void addSoldIce(Eis eis) {

        if (currentSoldIce.containsKey(eis)) {
            currentSoldIce.put(eis, currentSoldIce.get(eis) + 1);
            return;
        }
        currentSoldIce.put(eis, 1);

    }

    // View holt sich dies
    public float getCurrentValue() {
        return getSum(currentSoldIce);

    }


    // zählt currentsoldice zu daily dazu
    // Wert addition kommt vom paymentmanager
    public void finishCurrentSale() {

        currentSoldIce.forEach((key, value) -> {
                    if (dailySoldIce.containsKey(key)) {
                        dailySoldIce.put(key, dailySoldIce.get(key) + value);
                    } else {
                        dailySoldIce.put(key, value);
                    }

                }

        );


        currentSoldIce.clear();
    }


    public void printDay() {
        System.out.println("DAILY SALES:");
        dailySoldIce.forEach((key, value) -> System.out.println(key.name + ":" + value));
    }

    public void printCurr() {
        System.out.println("Current sales:");
        currentSoldIce.forEach((key, value) -> System.out.println(key.name + ":" + value));
    }
}