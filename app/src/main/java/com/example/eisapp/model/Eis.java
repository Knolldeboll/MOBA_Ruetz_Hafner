package com.example.eisapp.model;

import android.graphics.Color;

import java.io.Serializable;

public class Eis implements Serializable {

    String name;
    float preis;
    int backGroundColor;
    int textColor;

    public Eis(String name, float preis, int bgcolor) {
        this.name = name;
        this.preis = preis;
        this.backGroundColor = bgcolor;

        if (bgcolor > -8388607) {
            this.textColor = Color.BLACK;
        } else {
            this.textColor = Color.WHITE;
        }

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


    public int getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
}
