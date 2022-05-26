package com.example.eisapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eises = new ArrayList<Eis>();

        //Dummydaten erzeugen
        //eises.add(new Eis("popel"));
        eises.add(new Eis("schoko"));
        eises.add(new Eis("erdbeer"));

        // Rv holen
        RecyclerView rv = (RecyclerView) findViewById(R.id.recv);

        // Adapter für die daten erzeugen
        ea = new EisAdapter(eises);

        // beim rv den adapter setzen

        rv.setAdapter(ea);

        //rv.setHasFixedSize(true);

        //Layoutmanager setzen
        rv.setLayoutManager(new LinearLayoutManager(this));

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