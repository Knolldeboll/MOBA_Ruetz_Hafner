package com.example.eisapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;
import com.example.eisapp.model.Eis;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CurrentEisAdapter extends RecyclerView.Adapter<com.example.eisapp.model.CurrentEisAdapter.CurrentViewHolder> {

    private LinkedHashMap<Eis,Integer> datalist;
    private List<Eis> eislist;
    private List<Integer> anzlist;
    private OverviewFragment overviewFragment;


    public class CurrentViewHolder extends RecyclerView.ViewHolder{

        public TextView eisNameText;
        public TextView eisAnzahlText;
        public TextView eisSummeText;

        public CurrentViewHolder(View currentView){

            super(currentView);
            eisNameText = (TextView) currentView.findViewById(R.id.NameText);
            eisAnzahlText = (TextView) currentView.findViewById(R.id.AmountText);
            eisSummeText = (TextView) currentView.findViewById(R.id.PriceText);
        }
    }

    public CurrentEisAdapter(LinkedHashMap<Eis,Integer> eisList, OverviewFragment overviewFragment) {
        this.overviewFragment = overviewFragment;
        datalist = eisList;
        // Noch separieren
        this.eislist = new ArrayList<Eis>(eisList.keySet());
        this.anzlist = new ArrayList<Integer>(eisList.values());

    }

    @NonNull
    @Override
    public com.example.eisapp.model.CurrentEisAdapter.CurrentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View currentView = inflater.inflate(R.layout.overviewitemlayout,parent,false);
        com.example.eisapp.model.CurrentEisAdapter.CurrentViewHolder currentViewHolder = new com.example.eisapp.model.CurrentEisAdapter.CurrentViewHolder(currentView);

        currentView.findViewById(R.id.removeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Economy economy = Economy.getInstance();
                for (Eis eis : eislist) {
                    if (eis.getName().equals(currentViewHolder.eisNameText.getText())) {

                        // Problem: wird zwar aus economy entfernt, aber nicht hier aus der liste!
                        economy.removeSoldIce(eis);
                        datalist = economy.currentSoldIce;
                        eislist = new ArrayList<Eis>(datalist.keySet());
                        anzlist = new ArrayList<Integer>(datalist.values());
                        OverviewFragment.currentEisAdapter.notifyDataSetChanged();
                        TextView totalText = (TextView) overviewFragment.getActivity().findViewById(R.id.totalText);
                        totalText.setText(String.format("%.2f", economy.getCurrentValue()) + "€");
                        break;
                    }
                }
            }
        });

        return currentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.eisapp.model.CurrentEisAdapter.CurrentViewHolder holder, int position) {
        Eis eissorte = this.eislist.get(position);
        int anzahl = this.anzlist.get(position);
        float summe = eissorte.getPreis() * anzahl;

        TextView t1 = holder.eisNameText;
        t1.setText(eissorte.getName());

        TextView t2 = holder.eisAnzahlText;
        t2.setText("x "+ anzahl);

        TextView t3 = holder.eisSummeText;
        t3.setText("= " + String.format("%.2f", summe) + "€");

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
