package com.example.eisapp.model;

import android.graphics.Color;

import java.io.Serializable;

public class Eis implements Serializable {

    String name;
    float preis;
    Color backGroundColor;
    // TODO: Image irgendwie speichern, als pfad oder so

    public Eis(String name, float preis) {
        this.name = name;
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    public Color getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
}
