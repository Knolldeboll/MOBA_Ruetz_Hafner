package com.example.eisapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eisapp.model.Marke;
import com.example.eisapp.model.MarkenManager;
import com.example.eisapp.model.Economy;
import com.example.eisapp.model.Eis;
import com.example.eisapp.model.PaymentHandler;

public class MainActivity extends AppCompatActivity {

    //Daten von Model per Observer übergeben
    Economy eco;
    PaymentHandler pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MarkenManager markenManager = MarkenManager.getInstance(this);
       // markenManager.fillWithExampleData();
        markenManager.printList();




        eco = Economy.getInstance();
        pay = new PaymentHandler();

        // addSoldIce wird dann von den ActionHandlern der Buttons aufgerufen
        //Dies alles ist nur zum testen

        // Eis adden blablabla

        //Dann : press auf zahlen!


        Marke m = new Marke("Langneso");
        m.addEis(new Eis("Vanillollololol",2.0f));
        markenManager.addBrand(m);
        //eco.printDay();

        eco.addSoldIce(markenManager.marken.get(1).sorten.get(1));
        eco.addSoldIce(markenManager.marken.get(3).sorten.get(0));


        pay.currentSum = eco.getCurrentValue();
        // Wenn summenfeld geändert: ändere auch currentvalue im pay
        //pay.currentSum = inputfieldsum.value oder so
        //Tagestrinkgeld errechnet sich dann aus daylist.getsum - dailysum

        //gegeben entern!
        // on enter:
        //pay.getChange(inputfieldgegeben.value)
        System.out.println(pay.getChange(50));


        // Wenn <0: nochmal! zu wenig gegeben!

        // Wenn >=0 : anzeige im rückgeldfeld
        //Dann : pay

        pay.pay();

        eco.printDay();

    }
}