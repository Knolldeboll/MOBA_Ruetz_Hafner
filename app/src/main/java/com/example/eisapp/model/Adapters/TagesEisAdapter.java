package com.example.eisapp.model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;
import com.example.eisapp.model.Eis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

// Zuständig für den RecyclerView im Tagesabschluss-Fragment
public class TagesEisAdapter extends RecyclerView.Adapter<TagesEisAdapter.DayViewHolder> {

    private LinkedHashMap<Eis, Integer> datalist;
    private List<Eis> eislist;
    private List<Integer> anzlist;

    public class DayViewHolder extends RecyclerView.ViewHolder {

        public TextView eisNameText;
        public TextView eisAnzahlText;
        public TextView eisSummeText;

        public DayViewHolder(View dayView) {

            super(dayView);
            eisNameText = (TextView) dayView.findViewById(R.id.finishNameText);
            eisAnzahlText = (TextView) dayView.findViewById(R.id.finishAmountText);
            eisSummeText = (TextView) dayView.findViewById(R.id.finishPriceText);


        }

    }

    public TagesEisAdapter(LinkedHashMap<Eis, Integer> eisList) {
        datalist = eisList;
        this.eislist = new ArrayList<Eis>(eisList.keySet());
        this.anzlist = new ArrayList<Integer>(eisList.values());
    }


    @NonNull
    @Override
    public TagesEisAdapter.DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View dayView = inflater.inflate(R.layout.finishitemlayout, parent, false);
        DayViewHolder dayViewHolder = new DayViewHolder(dayView);

        return dayViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {

        Eis eissorte = this.eislist.get(position);
        int anzahl = this.anzlist.get(position);
        float summe = Math.round((eissorte.getPreis() * anzahl) * 100f) / 100f;

        TextView t1 = holder.eisNameText;
        t1.setText(eissorte.getName() + " (" + eissorte.getPreis() + "€)");

        TextView t2 = holder.eisAnzahlText;
        t2.setText("x " + anzahl);

        TextView t3 = holder.eisSummeText;
        t3.setText("= " + summe + "€");

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

}
