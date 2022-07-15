package com.example.eisapp.model;

import android.graphics.Color;

import java.io.Serializable;

public class Eis implements Serializable {

    String name;
    float preis;
    int backGroundColor;
    int textColor;
    // TODO: Image irgendwie speichern, als pfad oder so

    public Eis(String name, float preis, int bgcolor) {
        this.name = name;
        this.preis = preis;
        this.backGroundColor = bgcolor;

        if(bgcolor > - 8388607){
            this.textColor = Color.BLACK;
        }else{
            this.textColor = Color.WHITE;
        }
         // -1   8.388.607
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

    public int getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
}
