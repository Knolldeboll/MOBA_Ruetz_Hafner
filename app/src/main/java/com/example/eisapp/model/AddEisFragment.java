package com.example.eisapp.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.example.eisapp.R;

import java.util.ArrayList;
import java.util.List;

public class AddEisFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static EditText marketext;
    public static EditText sortetext;
    public static EditText preistext;
    public static Spinner markenspinner;
    public static Button button;
    public static ToggleButton toggleButton;

    public AddEisFragment(){

    }

    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.addicefragmentlayout, container, false);

        // TODO: Ersetzen durch auswahl aus liste
        marketext = (EditText) view.findViewById(R.id.editTextMarke);


        sortetext = (EditText) view.findViewById(R.id.editTextSorte);
        preistext = (EditText) view.findViewById(R.id.editTextNumberPreis);
        button = (Button) view.findViewById(R.id.addButton);
        toggleButton = (ToggleButton) view.findViewById(R.id.markeToggle);
        markenspinner = (Spinner) view.findViewById(R.id.markespinner);

        List<String> markennamen = new ArrayList<String>() ;
        for(Marke m : MarkenManager.getInstance(view.getContext()).marken){
            markennamen.add(m.name);
        };
        if(!markennamen.contains("Neue Marke")){
            markennamen.add("Neue Marke");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, markennamen);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        markenspinner.setAdapter(arrayAdapter);
        markenspinner.setOnItemSelectedListener(this);

        //markenspinner.setEnabled(false);
        //markenspinner.setVisibility(View.GONE);

        // TODO: Weitermachen

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(marketext.getText().toString() == null || sortetext.getText().toString()  == null || preistext.getText().toString()  == null){
                    System.out.println("Was vergessen!");
                    return;
                }else{
                    MarkenManager mm = MarkenManager.getInstance(view.getContext());
                    for (Marke m: mm.marken
                         ) {
                        // Wenn Marke schon da, füge eis hinzu
                        // TODO: Eleganter per Auswahl der Marke aus Liste + (neue Marke)
                        // TODO: Das funktioniert nämlich nicht mit texteingabe!

                        if(m.name.equals(marketext.getText())){
                            m.addEis(new Eis(sortetext.getText().toString(),Float.valueOf(preistext.getText().toString()).floatValue()));
                            System.out.println("Eis zu Marke hinzugefügt");
                            return;
                        }
                    }

                    Marke marke = new Marke(marketext.getText().toString());
                    marke.addEis(new Eis(sortetext.getText().toString(),Float.valueOf(preistext.getText().toString()).floatValue()));

                    mm.addBrand(marke);
                    System.out.println("Eis und Marke hinzugefügt");
                }

            }
        });

        toggleButton.setChecked(false);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    // An, neue marke
                    marketext.setVisibility(View.VISIBLE);
                    markenspinner.setVisibility(View.GONE);

                }else{
                    // Aus
                    marketext.setVisibility(View.GONE);
                    markenspinner.setVisibility(View.VISIBLE);
                }
            }
        });
        // 2 Textfelder, 1 button, überprüfen etc.

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String input = (String)adapterView.getItemAtPosition(i);
        System.out.println(input + "Selected!");
        // Wenn neue: input öffnen

        // Wenn alte: Text in markentext

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        System.out.println( "Nothing Selected!");

    }
}
