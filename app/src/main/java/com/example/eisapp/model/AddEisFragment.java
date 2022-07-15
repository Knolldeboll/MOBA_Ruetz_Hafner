package com.example.eisapp.model;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.example.eisapp.R;

import java.util.ArrayList;
import java.util.List;

public class AddEisFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText marketext;
    private EditText sortetext;
    private EditText preistext;
    private Spinner markenspinner;
    private Button button;
    private ToggleButton toggleButton;
    private ArrayAdapter<String> arrayAdapter;
    //private static String listenmarke;
    private static String neuemarke;

    public AddEisFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addicefragmentlayout, container, false);


        marketext = (EditText) view.findViewById(R.id.editTextMarke);
        sortetext = (EditText) view.findViewById(R.id.editTextSorte);
        preistext = (EditText) view.findViewById(R.id.editTextNumberPreis);
        button = (Button) view.findViewById(R.id.addButton);
        toggleButton = (ToggleButton) view.findViewById(R.id.markeToggle);
        markenspinner = (Spinner) view.findViewById(R.id.markespinner);


        // Problem: Markennamen wird beim hinzufügen nciht aktualisiert!
        List<String> markennamen = new ArrayList<String>();
        for (Marke m : MarkenManager.getInstance(view.getContext()).marken) {
            markennamen.add(m.name);
        }
        ;


        arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, markennamen);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        markenspinner.setAdapter(arrayAdapter);
        markenspinner.setOnItemSelectedListener(this);

        //markenspinner.setEnabled(false);
        //markenspinner.setVisibility(View.GONE);


        marketext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                neuemarke = editable.toString();
                System.out.println("Entered  " + neuemarke);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (sortetext.getText().toString().equals("") || preistext.getText().toString().equals("")) {
                    System.out.println("Was vergessen!");
                    return;

                } else {


                    if (toggleButton.isChecked()) {
                        // Neue Marke
                        if (!marketext.getText().toString().equals("")) {
                            Marke marke1 = new Marke(marketext.getText().toString());
                            marke1.addEis(new Eis(sortetext.getText().toString(), Float.valueOf(preistext.getText().toString()).floatValue()));
                            MarkenManager.getInstance(view.getContext()).addBrand(marke1);

                            MarkenManager.Instance.save();
                            System.out.println("Eis und Marke hinzugefügt");

                            Toast.makeText(view.getContext(), marke1.name + ": " + sortetext.getText().toString() + " hinzugefügt!", Toast.LENGTH_LONG).show();
                            markennamen.add(marke1.name);
                            arrayAdapter.notifyDataSetChanged();
                            // markenspinner.setAdapter(arrayAdapter);
                        }

                    } else {
                        if (markenspinner.getSelectedItem() != null) {

                            System.out.println(markenspinner.getSelectedItem().toString());
                            Marke m = MarkenManager.getInstance(view.getContext()).getMarkeByName(markenspinner.getSelectedItem().toString());
                            m.addEis(new Eis(sortetext.getText().toString(), Float.valueOf(preistext.getText().toString()).floatValue()));


                            MarkenManager.Instance.save();
                            System.out.println("Eis zu Marke hinzugefügt");
                            Toast.makeText(view.getContext(), m.name + " und " + sortetext.getText().toString() + " hinzugefügt!", Toast.LENGTH_LONG).show();


                        }


                    }


                }

            }
        });

        marketext.setVisibility(View.GONE);
        toggleButton.setChecked(false);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    // An, neue marke
                    marketext.setVisibility(View.VISIBLE);
                    markenspinner.setVisibility(View.GONE);

                } else {
                    // Aus
                    marketext.setVisibility(View.GONE);
                    markenspinner.setVisibility(View.VISIBLE);
                }
            }
        });


        return view;
    }


    // Liste Selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        // String input = (String)adapterView.getItemAtPosition(i);
        // System.out.println(input + "Selected!");

        // Marke m = getMarkeByName!
        // listenmarke = input;

        // Wenn neue: input öffnen

        // Wenn alte: Text in markentext

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        System.out.println("Nothing Selected!");

    }
}
