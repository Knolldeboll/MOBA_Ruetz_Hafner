package com.example.eisapp.model;

import static com.example.eisapp.model.FinishFragment.linearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

import java.util.ArrayList;

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
