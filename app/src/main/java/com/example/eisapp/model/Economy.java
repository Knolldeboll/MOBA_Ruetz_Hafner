package com.example.eisapp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Economy implements Serializable {

    LinkedHashMap<Eis, Integer> dailySoldIce;
    LinkedHashMap<Eis, Integer> currentSoldIce;

    public static Economy Instance;

    // Soll Summe aller verkauften Eis sein - wird nach finishCurrentSale aktualisiert
    public double dailySum;

    // Soll Summe aller Einahmen sein (mit Trinkgeld)
    public double dailyIncome;

    //Tagestrinkgeld
    public double dailyTip;

    public static Economy getInstance() {
        if (Instance == null) {

            Instance = new Economy();

        }
        return Instance;

    }

    public Economy() {

        //LinkedHashmap hat den vorteil der stabilen Reihenfolge
        // (Last added last)
        dailySoldIce = new LinkedHashMap<Eis, Integer>();
        currentSoldIce = new LinkedHashMap<Eis, Integer>();
    }

    private float getSum(LinkedHashMap<Eis, Integer> list) {

        float val = 0;
        for (Map.Entry<Eis, Integer> ent : list.entrySet()) {
            val += ent.getKey().preis * ent.getValue();

        }

        return Math.round((val) * 100f) / 100f;

    }

    // Entferne aus currentSoldIce-Liste
    // Kopplung an "x" Buttons
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

    // Zählt gedrücktes eis zu currentSoldIce dazu
    // Kopplung an Eisbutton
    public void addSoldIce(Eis eis) {

        if (currentSoldIce.containsKey(eis)) {
            currentSoldIce.put(eis, currentSoldIce.get(eis) + 1);
            return;
        }
        currentSoldIce.put(eis, 1);

    }

    public float getCurrentValue() {
        return getSum(currentSoldIce);

    }


    // Zählt currentSoldIce zu dailySoldIce dazu
    public void finishCurrentSale() {

        currentSoldIce.forEach((key, value) -> {
                    if (dailySoldIce.containsKey(key)) {
                        dailySoldIce.put(key, dailySoldIce.get(key) + value);
                    } else {
                        dailySoldIce.put(key, value);
                    }

                }

        );

        dailySum += Math.round((getSum(currentSoldIce)) * 100f) / 100f;
        dailyTip = Math.round((dailyIncome - dailySum) * 100f) / 100f;


        currentSoldIce.clear();
    }
}