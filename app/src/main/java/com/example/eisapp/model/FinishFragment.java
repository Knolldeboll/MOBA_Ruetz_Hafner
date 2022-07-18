package com.example.eisapp.model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.MainActivity;
import com.example.eisapp.R;

public class FinishFragment extends Fragment {

    public static RecyclerView recyclerView;
    public static TagesEisAdapter tagesEisAdapter;
    public static LinearLayoutManager linearLayoutManager;
    public static TextView sumText;
    public static TextView incomeText;
    public static TextView tipText;
    public static Button finishButton;

    public FinishFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finishfragmentlayout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.finishrecv);

        incomeText = (TextView) view.findViewById(R.id.finishIncome);
        sumText = (TextView) view.findViewById(R.id.finishSum);
        tipText = (TextView) view.findViewById(R.id.finishTip);
        finishButton = (Button) view.findViewById(R.id.finishbutton);

        tagesEisAdapter = new TagesEisAdapter(Economy.getInstance().dailySoldIce);
        linearLayoutManager = new LinearLayoutManager(view.getContext());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setAdapter(tagesEisAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        incomeText.setText("= " + Math.round((Economy.getInstance().dailyIncome) * 100f) / 100f + "€");
        sumText.setText("= " + Math.round((Economy.getInstance().dailySum) * 100f) / 100f + "€");
        tipText.setText("= " + Math.round((Economy.getInstance().dailyTip) * 100f) / 100f + "€");


        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());  // Evtl mit getActivity() ?
                alertDialogBuilder.setMessage("Sicher beenden ?");

                alertDialogBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        MainActivity.save = false;
                        getActivity().finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });

        return view;
    }

}
