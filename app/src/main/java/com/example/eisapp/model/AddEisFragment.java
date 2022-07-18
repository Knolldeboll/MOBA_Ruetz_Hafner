package com.example.eisapp.model;

import android.content.DialogInterface;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.eisapp.R;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import java.util.ArrayList;
import java.util.List;


// Fremdcode: ColorPickerView von skydoves: https://github.com/skydoves/ColorPickerView

public class AddEisFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText marketext;
    private EditText sortetext;
    private EditText preistext;
    private Spinner markenspinner;
    private Button addbutton;
    private TextView colortext;
    private ToggleButton toggleButton;
    private ArrayAdapter<String> arrayAdapter;
    private static String neuemarke;
    private int color;

    public AddEisFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addicefragmentlayout, container, false);


        marketext = (EditText) view.findViewById(R.id.editTextMarke);
        sortetext = (EditText) view.findViewById(R.id.editTextSorte);
        preistext = (EditText) view.findViewById(R.id.editTextNumberPreis);
        addbutton = (Button) view.findViewById(R.id.addButton);
        toggleButton = (ToggleButton) view.findViewById(R.id.markeToggle);
        markenspinner = (Spinner) view.findViewById(R.id.markespinner);
        colortext = (TextView) view.findViewById(R.id.colorText);
        color = 0;

        List<String> markennamen = new ArrayList<String>();
        for (Marke m : MarkenManager.getInstance(view.getContext()).marken) {
            markennamen.add(m.name);
        }


        arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, markennamen);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        markenspinner.setAdapter(arrayAdapter);
        markenspinner.setOnItemSelectedListener(this);


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


        colortext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerDialog.Builder(view.getContext())
                        .setTitle("ColorPicker Dialog")
                        .setPreferenceName("MyColorPickerDialog")
                        .setPositiveButton("OK",
                                new ColorEnvelopeListener() {
                                    @Override
                                    public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {

                                        // Set Selected Color!
                                        color = envelope.getColor();
                                        // Textfarbe S/W, je nach RGB-Wert
                                        if (color > -8388607) {
                                            colortext.setTextColor(Color.BLACK);
                                        } else {
                                            colortext.setTextColor(Color.WHITE);
                                        }

                                        colortext.setBackgroundColor(color);
                                    }
                                })
                        .setNegativeButton("Abbrechen",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })

                        .attachBrightnessSlideBar(true)  // the default value is true.
                        .attachAlphaSlideBar(false)
                        .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                        .show();

            }
        });


        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (sortetext.getText().toString().equals("") || preistext.getText().toString().equals("") || color == 0) {

                    Toast.makeText(view.getContext(), "Eingabe fehlt!", Toast.LENGTH_SHORT).show();

                    return;

                } else {


                    if (toggleButton.isChecked()) {
                        // Neue Marke
                        if (!marketext.getText().toString().equals("")) {
                            Marke marke1 = new Marke(marketext.getText().toString());
                            marke1.addEis(new Eis(sortetext.getText().toString(), Float.valueOf(preistext.getText().toString()).floatValue(), color));
                            MarkenManager.getInstance(view.getContext()).addBrand(marke1);

                            MarkenManager.getInstance(view.getContext()).save();
                            Toast.makeText(view.getContext(), marke1.name + " und " + sortetext.getText().toString() + " hinzugefügt!", Toast.LENGTH_SHORT).show();

                            markennamen.add(marke1.name);
                            arrayAdapter.notifyDataSetChanged();
                        }

                    } else {
                        // Bestehende Marke
                        if (markenspinner.getSelectedItem() != null) {

                            Marke m = MarkenManager.getInstance(view.getContext()).getMarkeByName(markenspinner.getSelectedItem().toString());
                            m.addEis(new Eis(sortetext.getText().toString(), Float.valueOf(preistext.getText().toString()).floatValue(), color));

                            MarkenManager.getInstance(view.getContext()).save();
                            Toast.makeText(view.getContext(), m.name + ": " + sortetext.getText().toString() + " hinzugefügt!", Toast.LENGTH_SHORT).show();

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

                    marketext.setVisibility(View.VISIBLE);
                    markenspinner.setVisibility(View.GONE);

                } else {

                    marketext.setVisibility(View.GONE);
                    markenspinner.setVisibility(View.VISIBLE);
                }
            }
        });


        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }


}
