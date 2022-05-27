package com.example.eisapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eisapp.model.Economy;
import com.example.eisapp.model.Eis;
import com.example.eisapp.model.EisAdapter;
import com.example.eisapp.model.PaymentHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Daten von Model per Observer übergeben
    Economy eco;
    PaymentHandler pay;
    List<Eis> eises;
    EisAdapter ea;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eises = new ArrayList<Eis>();


        //Dummydaten erzeugen
        //eises.add(new Eis("popel"));
        /*eises.add(new Eis("schoko"));
        eises.add(new Eis("erdbeer"));
        eises.add(new Eis("derdbeer"));
        eises.add(new Eis("ferdbeer"));
        eises.add(new Eis("rerdbeer"));
        eises.add(new Eis("kerdbeer"));
        eises.add(new Eis("perdbeer"));
        eises.add(new Eis("äerdbeer"));
        */
        // Rv holen
        RecyclerView rv = (RecyclerView) findViewById(R.id.recv);

        // Adapter für die daten erzeugen
        ea = new EisAdapter(eises);


        // beim rv den adapter setzen


        // IDEE FÜR DYNAMISCHE Unterlisten pro marken-"Item" : Recyclerview im recyclerview, oder einfach gridview darin
        rv.setAdapter(ea);

        rv.setHasFixedSize(true);

        //Layoutmanager setzen
        rv.setLayoutManager(new LinearLayoutManager(this));


/*
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eises.add(new Eis("neu"));
                ea.notifyItemInserted(ea.getItemCount());

            }
        });
        */

        //eco = Economy.getInstance();
        //pay = new PaymentHandler();

        // addSoldIce wird dann von den ActionHandlern der Buttons aufgerufen
        //Dies alles ist nur zum testen

        // Eis adden blablabla

        //Dann : press auf zahlen!
        //pay.currentSum = eco.getCurrentValue();
        // Wenn summenfeld geändert: ändere auch currentvalue im pay
        //pay.currentSum = inputfieldsum.value oder so
        //Tagestrinkgeld errechnet sich dann aus daylist.getsum - dailysum

        //gegeben entern!
        // on enter:
        //pay.getChange(inputfieldgegeben.value)
        //System.out.println(pay.getChange(50));


        // Wenn <0: nochmal! zu wenig gegeben!

        // Wenn >=0 : anzeige im rückgeldfeld
        //Dann : pay

        //pay.pay();



    }
}