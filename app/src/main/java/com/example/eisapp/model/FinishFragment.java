package com.example.eisapp.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

public class FinishFragment extends Fragment {

    public static RecyclerView recyclerView;
    public static TagesEisAdapter tagesEisAdapter;
    public static LinearLayoutManager linearLayoutManager;
    public static TextView sumText;
    public static TextView incomeText;
    public static TextView tipText;

    public FinishFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finishfragmentlayout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.finishrecv);

        incomeText = (TextView) view.findViewById(R.id.finishIncome);
        sumText = (TextView) view.findViewById(R.id.finishSum);
        tipText = (TextView) view.findViewById(R.id.finishTip);


        tagesEisAdapter = new TagesEisAdapter(Economy.getInstance().dailySoldIce);
        linearLayoutManager = new LinearLayoutManager(view.getContext());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setAdapter(tagesEisAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        incomeText.setText("= " + String.valueOf( Economy.Instance.dailyIncome) + "€");
        sumText.setText("= " + String.valueOf( Economy.Instance.dailySum) + "€");
        tipText.setText("= " + String.valueOf( Economy.Instance.dailyTip) + "€");



        return view;
    }

}
