package com.example.eisapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eisapp.model.MarkenManager;

public class MainActivity extends AppCompatActivity {

    //Daten von Model per Observer übergeben
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MarkenManager markenManager = new MarkenManager(this);
        markenManager.fillWithExampleData();
        markenManager.printList();
    }
}