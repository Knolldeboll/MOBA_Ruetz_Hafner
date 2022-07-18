package com.example.eisapp.model;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.MainActivity;
import com.example.eisapp.R;

public class SaleFragment extends Fragment {

    public static RecyclerView recyclerView;
    public static SaleMarkenAdapter markenAdapter;
    public static GridLayoutManager childLayout;

    public SaleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.salefragmentlayout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recv);


        markenAdapter = new SaleMarkenAdapter(MarkenManager.getInstance(view.getContext()).marken, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView temptv = (TextView) view.findViewById(R.id.textVieweis);
                System.out.println(temptv.getText());
                Economy.getInstance().addSoldIce(MarkenManager.getInstance(view.getContext()).getEisByName((String) temptv.getText()));
                MainActivity.lastEis = MarkenManager.getInstance(view.getContext()).getEisByName((String) temptv.getText());

                TextView totaltext = getActivity().findViewById(R.id.totalText);
                if (totaltext != null) {
                    totaltext.setText(String.valueOf(Economy.getInstance().getCurrentValue()) + "€");
                }

            }
        }, R.drawable.markeitem_background);

        recyclerView.setAdapter(markenAdapter);
        recyclerView.setHasFixedSize(true);

        //Layoutmanager setzen Parent (Marken-RecyclerView)
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        childLayout = new GridLayoutManager(view.getContext(), 3);
        return view;
    }
}
