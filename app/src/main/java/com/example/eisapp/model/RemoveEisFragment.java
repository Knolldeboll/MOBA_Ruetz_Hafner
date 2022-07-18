package com.example.eisapp.model;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.MainActivity;
import com.example.eisapp.R;

public class RemoveEisFragment extends Fragment {


    public RecyclerView recyclerView;
    public SaleMarkenAdapter markenAdapter;

    public RemoveEisFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentview = inflater.inflate(R.layout.salefragmentlayout, container, false);
        recyclerView = (RecyclerView) fragmentview.findViewById(R.id.recv);


        markenAdapter = new SaleMarkenAdapter(MarkenManager.getInstance(this.getContext()).marken, new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Eis removeeis = MarkenManager.getInstance(view.getContext()).getEisByName((String) ((TextView) view).getText());
                Marke mark = MarkenManager.getInstance(view.getContext()).getMarkeByEis(removeeis);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                alertDialogBuilder.setMessage("Sicher \"" + mark.name + ": " + removeeis.name + "\" entfernen ?");

                alertDialogBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mark.removeEis(removeeis);
                        markenAdapter.notifyDataSetChanged();
                        Toast.makeText(fragmentview.getContext(), mark.name + ": " + removeeis.getName() + " entfernt!", Toast.LENGTH_SHORT).show();
                        MarkenManager.getInstance(view.getContext()).save();


                    }
                });

                alertDialogBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(view.getContext(), "Nicht entfernt!", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        }, R.drawable.markeitem_background_delete);

        recyclerView.setAdapter(markenAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragmentview.getContext()));

        return fragmentview;
    }


}
