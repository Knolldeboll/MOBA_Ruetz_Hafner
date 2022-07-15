package com.example.eisapp.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

public class FinishFragment extends Fragment {

    public static RecyclerView recyclerView;
    public static TagesEisAdapter tagesEisAdapter;
    public static LinearLayoutManager linearLayoutManager;

    public FinishFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finishfragmentlayout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.finishrecv);
        tagesEisAdapter = new TagesEisAdapter(Economy.getInstance().dailySoldIce);
        linearLayoutManager = new LinearLayoutManager(view.getContext());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setAdapter(tagesEisAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }

}
