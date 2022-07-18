package com.example.eisapp.model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;
import com.example.eisapp.model.Marke;

import java.util.List;


public class SaleMarkenAdapter extends RecyclerView.Adapter<SaleMarkenAdapter.ViewHolder> {
    private List<Marke> datalist;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public View.OnClickListener passOcl;
    public int backgroundID;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView t1;
        public RecyclerView childrecv;
        public ConstraintLayout constL;

        public ViewHolder(View itemView) {


            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.textView1);
            childrecv = (RecyclerView) itemView.findViewById(R.id.eisrv);
            constL = (ConstraintLayout) itemView.findViewById(R.id.constLayout);
        }
    }

    // Da dieser RecyclerView/Adapter zweimal in verschiedenen Kontexten (SaleFragment / RemoveFragment) mit verschiedenen Eigenschaften verwendet wird,
    // werden diese Eigenschaften (drawable, Eissorten-OCL) im Konstruktur übergeben.
    // Vorallem beim OCL der Eissorten ist das wichtig, da wir  auf diese Elemente nur über den verschachtelten SaleEisAdapter zugreifen konnten.

    public SaleMarkenAdapter(List<Marke> markenList, View.OnClickListener onClickListener, int drawId) {
        datalist = markenList;
        passOcl = onClickListener;
        backgroundID = drawId;

        System.out.println("Im SaleMarkenAdapter: " + passOcl);
        System.out.println("Datalist Im SaleMarkenAdapter: " + datalist);


    }

    @Override
    public SaleMarkenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View markenView = inflater.inflate(R.layout.markeitemlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(markenView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Marke marke = datalist.get(position);
        TextView t1 = holder.t1;
        t1.setText(marke.name);

        LinearLayoutManager llm = new LinearLayoutManager(holder.childrecv.getContext());
        llm.setOrientation(RecyclerView.HORIZONTAL);

        // Adapter für den Eissorten-RecyclerView setzen
        SaleEisAdapter eisAdapter = new SaleEisAdapter(marke.sorten, passOcl);

        holder.childrecv.setLayoutManager(llm);
        holder.childrecv.setAdapter(eisAdapter);
        holder.childrecv.setRecycledViewPool(viewPool);
        holder.constL.setBackgroundResource(backgroundID);

    }

    @Override
    public int getItemCount() {

        return datalist.size();
    }
}
