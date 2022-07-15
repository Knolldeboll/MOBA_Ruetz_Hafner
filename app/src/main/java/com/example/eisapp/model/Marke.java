package com.example.eisapp.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Marke implements Serializable {

    String name;
    public List<Eis> sorten;

    public Marke(String name) {
        this.name = name;
    }

    public void addEis(Eis eis) {
        if (sorten == null) {
            sorten = new ArrayList<>();
        }
        sorten.add(eis);
    }

    public void removeEis(Eis eis) {

        if (sorten.contains(eis)) {
            sorten.remove(eis);
            if (sorten.size() == 0) {
                MarkenManager.Instance.removeBrand(this);
            }

        } else {
            return;
        }


    }

}
