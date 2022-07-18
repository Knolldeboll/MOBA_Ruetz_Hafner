package com.example.eisapp.model.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;
import com.example.eisapp.model.Adapters.CurrentEisAdapter;
import com.example.eisapp.model.Economy;

public class OverviewFragment extends Fragment {

    public static RecyclerView recyclerView;
    public static CurrentEisAdapter currentEisAdapter;
    public static LinearLayoutManager linearLayoutManager;


    public OverviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overviewfragment, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.currentrecv);

        currentEisAdapter = new CurrentEisAdapter(Economy.getInstance().currentSoldIce, this);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(currentEisAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}
