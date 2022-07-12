package com.example.eisapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

import java.util.List;

public class SaleEisAdapter extends RecyclerView.Adapter<SaleEisAdapter.ChildViewHolder> {

    private List<Eis> datalist;


    public class ChildViewHolder extends RecyclerView.ViewHolder{

        public TextView et1;


        public ChildViewHolder(View itemView){

            // Wie mit variablen anzahlen ?
            // Evtl ein
            super(itemView);

            // Die textviews aus der xml holen
            et1 = (TextView) itemView.findViewById(R.id.textVieweis);


        }

    }

    public SaleEisAdapter(List<Eis> eisList){datalist = eisList; }

    @Override
    public SaleEisAdapter.ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout - dh das xml-layout hier initial anwenden, noch aber leer
        View eisView = inflater.inflate(R.layout.childitemlayout, parent, false);

        // Return a new holder instance - hat dann das xmllayout gespeichert und inflated
       ChildViewHolder viewHolder = new ChildViewHolder(eisView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChildViewHolder holder, int position) {

        // die anzuzeigenden daten wählen
       Eis eis = datalist.get(position);

        //hier dann die im viewholder gespeicherten elemente aus dem layout holen und bestücken

        TextView eist1 = holder.et1;
        eist1.setText(eis.name);

    }

    @Override
    public int getItemCount() {


        return datalist.size();
    }


}
