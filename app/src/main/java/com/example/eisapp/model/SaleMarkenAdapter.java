package com.example.eisapp.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

import java.util.List;


public class SaleMarkenAdapter extends RecyclerView.Adapter<SaleMarkenAdapter.ViewHolder> {
    private List<Marke> datalist;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    public RecyclerView accessChildRecv; // Für zugriff später von aussen!?

    public View.OnClickListener passOcl;
    public int backgroundID;

    // Der viewholder stellt struktur zur späteren speicherung der view, wie sie in der xml definiert ist
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView t1;
        public RecyclerView childrecv;
        public ConstraintLayout constL;

        public ViewHolder(View itemView) {


            super(itemView);

            // Die textviews aus der xml holen
            t1 = (TextView) itemView.findViewById(R.id.textView1);
            childrecv = (RecyclerView) itemView.findViewById(R.id.eisrv);
            constL = (ConstraintLayout) itemView.findViewById(R.id.constLayout);
            accessChildRecv = childrecv;

            // t2 = (TextView) itemView.findViewById(R.id.textView2);
            // t3 = (TextView) itemView.findViewById(R.id.textView3);
        }

    }

    // Hier werden die anzuzeigenden eise gespiechert

    // Beim erstellen des Objekts die eisliste hier befüllen mit werten von aussen, z.b. aus dem model
    public SaleMarkenAdapter(List<Marke> markenList, View.OnClickListener onClickListener, int drawId) {
        datalist = markenList;
        passOcl = onClickListener;

        // TODO: Color ersetzen durch drawable
        backgroundID = drawId;

        System.out.println("Im SaleMarkenAdapter: " + passOcl);
        System.out.println("Datalist Im SaleMarkenAdapter: " + datalist);


    }

    // hier wird beim initialisieren das tatsächliche layout (view) in den viewholder gespeichert!
    @Override
    public SaleMarkenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout - dh das xml-layout hier initial anwenden, noch aber leer
        View markenView = inflater.inflate(R.layout.markeitemlayout, parent, false);

        // Return a new holder instance - hat dann das xmllayout gespeichert und inflated
        ViewHolder viewHolder = new ViewHolder(markenView);
        return viewHolder;
    }

    // Nun daten in den viewholder - wie werden die daten in die view gesetzt ?
    // wird per position gemacht! bzw für alle
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // die anzuzeigenden daten wählen
        Marke marke = datalist.get(position);

        //hier dann die im viewholder gespeicherten elemente aus dem layout holen und bestücken
        TextView t1 = holder.t1;
        t1.setText(marke.name);

        // Hier den layoutmanager für den child-rv erstellen
        LinearLayoutManager llm = new LinearLayoutManager(holder.childrecv.getContext());
        llm.setOrientation(RecyclerView.HORIZONTAL);

        // Hier den Adapter für den child-rv erstellen und die daten übergeben
        SaleEisAdapter eisAdapter = new SaleEisAdapter(marke.sorten, passOcl);

        // Die erstellten sachen setzen, auch den viewpool
        holder.childrecv.setLayoutManager(llm);
        holder.childrecv.setAdapter(eisAdapter);
        holder.childrecv.setRecycledViewPool(viewPool);


        //holder.constL.setBackgroundColor(color);
        holder.constL.setBackgroundResource(backgroundID);

    }

    @Override
    public int getItemCount() {

        return datalist.size();
    }
}
