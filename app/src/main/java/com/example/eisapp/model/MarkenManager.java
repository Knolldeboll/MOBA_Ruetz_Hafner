package com.example.eisapp.model;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MarkenManager {

    Context context;
    List<Marke> marken;
    private final String filename = "marken.txt";

    //TODO: Marken im Konstruktor(evtl) aus einer Datei ziehen
    public MarkenManager(Context context) {
        this.context = context;
        this.marken = getBrandsFromFile();
    }

    private void saveBrandsToFile(List<Marke> data) {
        try {
            FileOutputStream fos = context.openFileOutput(this.filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Brands saved!");
    }

    private List<Marke> getBrandsFromFile() {
        System.out.println("Called getBrandsFromFile()");
        try {
            FileInputStream fis = context.openFileInput(this.filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Marke> returnList = (List<Marke>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("read brands from file!");
            return returnList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("returning null...");
        return null;
    }

    public void addBrand(Marke m){
        this.marken = getBrandsFromFile();
        if (marken == null) {
            marken = new ArrayList<>();
        }
        marken.add(m);
        //TODO: Speichern in der Datei
        saveBrandsToFile(marken);
    }

    public void printList() {
        if(marken == null) {
            System.out.println("List is empty!!");
            return;
        }
        for (Marke m : marken) {
            System.out.println("Marke: " + m.name);
            System.out.println("Sorten: ");
            for (Eis e : m.sorten) {
                System.out.println(e.name + ", Preis: " + e.preis);
            }
        }
    }

    public void fillWithExampleData() {
        List<Marke> data = new ArrayList<>();
        Marke schöller = new Marke("Schöller");
        schöller.addEis(new Eis("Kaktus", 1.50f));
        schöller.addEis(new Eis("Pirulo", 2.00f));
        schöller.addEis(new Eis("Frutti", 1.40f));

        Marke magnum = new Marke("Magnum");
        magnum.addEis(new Eis("Classic", 2.00f));
        magnum.addEis(new Eis("Black Almond", 2.20f));
        magnum.addEis(new Eis("White Almond", 2.20f));

        data.add(schöller);
        data.add(magnum);

        saveBrandsToFile(data);
    }
}
