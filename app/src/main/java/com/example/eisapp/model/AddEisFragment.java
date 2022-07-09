package com.example.eisapp.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.eisapp.R;

public class AddEisFragment extends Fragment {

    public static EditText marketext;
    public static EditText sortetext;
    public static EditText preistext;
    public static Button button;


    public AddEisFragment(){

    }

    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.addicefragmentlayout, container, false);

        marketext = (EditText) view.findViewById(R.id.editTextMarke);
        sortetext = (EditText) view.findViewById(R.id.editTextSorte);
        preistext = (EditText) view.findViewById(R.id.editTextNumberPreis);

        button = (Button) view.findViewById(R.id.addButton);

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
        // 2 Textfelder, 1 button, überprüfen etc.

        return view;
    }

}
