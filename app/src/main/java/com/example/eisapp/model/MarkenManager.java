package com.example.eisapp.model;

import java.util.List;

public class MarkenManager {

    List<Marke> marken;

    //TODO: Marken im Konstruktor(evtl) aus einer Datei ziehen



    public void addBrand(Marke m){
        marken.add(m);
        //TODO: Speichern in der Datei

    }

}
