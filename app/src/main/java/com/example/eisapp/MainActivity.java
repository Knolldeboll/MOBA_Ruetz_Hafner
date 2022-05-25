package com.example.eisapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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


        eco = Economy.getInstance();
        pay = new PaymentHandler();

        // addSoldIce wird dann von den ActionHandlern der Buttons aufgerufen
        //Dies alles ist nur zum testen

        // Eis adden blablabla

        //Dann : press auf zahlen!
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



    }
}