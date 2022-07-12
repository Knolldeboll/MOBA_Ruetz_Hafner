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

    public static MarkenManager Instance;

    public static MarkenManager getInstance(Context callingContext){
        if(Instance == null){
            Instance = new MarkenManager(callingContext);
        }
            return Instance;
    }
    Context context;

    public List<Marke> marken;

    private final String filename = "marken.txt";


    public MarkenManager(Context context) {
        this.context = context;
        //this.fillWithExampleData();
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

            System.out.println("AAAAAAA");
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println("BBBBBB");
            List<Marke> returnList = (List<Marke>) ois.readObject();
            System.out.println("CCCCCC");
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
        //this.marken = getBrandsFromFile();
        if (marken == null) {
            marken = new ArrayList<>();
        }
        marken.add(m);

        saveBrandsToFile(marken);
    }

    public void removeBrand(Marke m){
        System.out.println("remove  " + marken.size()+ "  "+ m.name);
        //this.marken = getBrandsFromFile();
        if(marken == null || !marken.contains(m)) {
            return;
        }

        marken.remove(m);

        saveBrandsToFile(marken);
        printList();

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

    // TODO: Prüfen, ob datei bisher leer
    public void fillWithExampleData() {
        List<Marke> data = new ArrayList<>();
        Marke schöller = new Marke("Schöller");
        schöller.addEis(new Eis("Kaktus", 1.50f));
        schöller.addEis(new Eis("Pirulo", 2.00f));
        schöller.addEis(new Eis("Frutti", 1.40f));
        schöller.addEis(new Eis("Nutti", 1.40f));
        schöller.addEis(new Eis("Pluutti", 1.40f));

        Marke magnum = new Marke("Magnum");
        magnum.addEis(new Eis("Classic", 2.00f));
        magnum.addEis(new Eis("Black Almond", 2.20f));
        magnum.addEis(new Eis("White Almond", 2.20f));

        data.add(schöller);
        data.add(magnum);

        saveBrandsToFile(data);
    }

    public Eis getEisByName(String name){
        for(Marke m : Instance.marken){
            for (Eis e : m.sorten){
                if(e.name == name){
                    return e;
                }
            }
        }

        return null;
    }
}
