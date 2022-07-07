package com.example.eisapp.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.eisapp.R;

public class MenuFragment extends Fragment {

    public static Button button1;


    public MenuFragment(){

    }
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    // TODO: Problem! Durchklickbar! Evtl das Salefragment solange disabeln o.ä.

    View view = inflater.inflate(R.layout.menufragmentlayout, container, false);

    button1 = view.findViewById(R.id.menubutton1);
    button1.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view) {
        System.out.println("Menu button pressed");
        }
    });

    return view;
}

}
