package com.example.eisapp.model;

import android.content.Context;
import android.graphics.Color;

import com.example.eisapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MarkenManager {

    public static MarkenManager Instance;

    public static MarkenManager getInstance(Context callingContext) {
        if (Instance == null) {
            Instance = new MarkenManager(callingContext);
        }
        return Instance;
    }

    Context context;

    public List<Marke> marken;


    private final String filename = "marken.txt";


    public MarkenManager(Context context) {
        this.context = context;
        this.marken = getBrandsFromFile();
    }

    public void save() {
        saveBrandsToFile(marken);
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
            System.out.println(" == Fail to load file! 1");
            // finnWithExympleData, try again


        } catch (IOException e) {

            e.printStackTrace();
            System.out.println(" == Fail to load file! 2");
            fillWithExampleData();
            return getBrandsFromFile();

        } catch (ClassNotFoundException e) {
            System.out.println(" == Fail to load file! 3");

            e.printStackTrace();


        }
        // Never Reached
        return null;
    }

    public void addBrand(Marke m) {
        //this.marken = getBrandsFromFile();
        if (marken == null) {
            marken = new ArrayList<>();
        }
        marken.add(m);

        saveBrandsToFile(marken);
    }

    public void removeBrand(Marke m) {
        System.out.println("remove  " + marken.size() + "  " + m.name);
        //this.marken = getBrandsFromFile();
        if (marken == null || !marken.contains(m)) {
            return;
        }

        marken.remove(m);

        saveBrandsToFile(marken);
        printList();

    }

    public void printList() {
        if (marken == null) {
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
        // TODO: Gescheide Namen/Farben + Mehr marken!
        List<Marke> data = new ArrayList<>();
        Marke schöller = new Marke("Schöller");
        schöller.addEis(new Eis("Kaktus", 2.00f, Color.parseColor("#15612b")));
        schöller.addEis(new Eis("Pirulo", 2.20f, Color.YELLOW));
        schöller.addEis(new Eis("Bum Bum", 2.50f, Color.parseColor("#d41f23")));
        schöller.addEis(new Eis("Himbi", 2.50f, Color.parseColor("#d41e6b")));
        schöller.addEis(new Eis("Smarties", 1.40f, Color.parseColor("#6635e2")));

        Marke magnum = new Marke("Magnum");
        magnum.addEis(new Eis("Classic", 2.00f, Color.parseColor("#5a1212")));
        magnum.addEis(new Eis("Black Almond", 2.20f, Color.parseColor("#000000")));
        magnum.addEis(new Eis("White", 2.20f, Color.parseColor("#ededed")));


        Marke benjerrys = new Marke("Ben & Jerry's");
        benjerrys.addEis(new Eis("Chocolate Fudge Brownie", 3.50f, Color.parseColor("#5f4600")));
        benjerrys.addEis(new Eis("Cookie Dough", 3.50f, Color.parseColor("#ffd3a6")));
        benjerrys.addEis(new Eis("Peanut Butter Cup", 3.50f, Color.parseColor("#fd9146")));

        data.add(schöller);
        data.add(magnum);
        data.add(benjerrys);

        saveBrandsToFile(data);
    }

    public Eis getEisByName(String name) {
        for (Marke m : Instance.marken) {
            for (Eis e : m.sorten) {
                if (e.name == name) {
                    return e;
                }
            }
        }

        return null;
    }

    public Marke getMarkeByName(String name) {
        for (Marke m : Instance.marken) {
            if (m.name == name) {
                return m;
            }
        }
        return null;
    }

    public Marke getMarkeByEis(Eis eis) {
        for (Marke m : Instance.marken) {
            if (m.sorten.contains(eis)) {
                return m;
            }
        }
        return null;
    }
}
