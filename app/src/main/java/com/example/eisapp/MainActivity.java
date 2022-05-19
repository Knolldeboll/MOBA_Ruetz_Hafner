package com.example.eisapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eisapp.model.Economy;
import com.example.eisapp.model.Eis;

public class MainActivity extends AppCompatActivity {

    //Daten von Model per Observer übergeben
    Economy eco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Eis eins = new Eis("eins");
        Eis zwei = new Eis("zwei");
        Eis drei = new Eis("dreoi");

        // TODO: Statt dem Array alle verfügbaren eissorten übergeben, aus markenmanager zb
        //aber das passiert in der ecocolasse
        eco = new Economy(new Eis[]{eins, zwei, drei});

        // addSoldIce wird dann von den ActionHandlern der Buttons aufgerufen
        //Dies alles ist nur zum testen
        eco.addSoldIce(drei);
        eco.addSoldIce(zwei);

        eco.addSoldIce(zwei);
        eco.addSoldIce(eins);
        eco.addSoldIce(zwei);


        eco.printCurr();

        eco.finishCurrentSale();
        eco.printDay();

        eco.addSoldIce(drei);
        eco.addSoldIce(zwei);

        eco.addSoldIce(zwei);
        eco.addSoldIce(eins);
        eco.addSoldIce(zwei);

        eco.finishCurrentSale();
        eco.printDay();
       // eco.printCurr();

    }
}