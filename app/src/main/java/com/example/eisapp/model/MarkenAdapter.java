package com.example.eisapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

import java.util.List;


public class MarkenAdapter extends RecyclerView.Adapter<MarkenAdapter.ViewHolder> {
    private List<Marke> datalist;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    // Der viewholder stellt struktur zur späteren speicherung der view, wie sie in der xml definiert ist
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView t1;
        public RecyclerView recv;

        public ViewHolder(View itemView){


            super(itemView);

            // Die textviews aus der xml holen
            t1 = (TextView) itemView.findViewById(R.id.textView1);
            recv = (RecyclerView) itemView.findViewById(R.id.eisrv);
           // t2 = (TextView) itemView.findViewById(R.id.textView2);
           // t3 = (TextView) itemView.findViewById(R.id.textView3);
        }

    }

    // Hier werden die anzuzeigenden eise gespiechert

    // Beim erstellen des Objekts die eisliste hier befüllen mit werten von aussen, z.b. aus dem model
    public MarkenAdapter(List<Marke> markenList){datalist = markenList; }

    // hier wird beim initialisieren das tatsächliche layout (view) in den viewholder gespeichert!
    @Override
    public MarkenAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout - dh das xml-layout hier initial anwenden, noch aber leer
        View markenView = inflater.inflate(R.layout.itemlayout, parent, false);

        // Return a new holder instance - hat dann das xmllayout gespeichert und inflated
        ViewHolder viewHolder = new ViewHolder(markenView);
        return viewHolder;
    }

    // Nun daten in den viewholder - wie werden die daten in die view gesetzt ?
    // wird per position gemacht! bzw für alle
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // die anzuzeigenden daten wählen
        Marke marke = datalist.get(position);

        //hier dann die im viewholder gespeicherten elemente aus dem layout holen und bestücken
        TextView t1 = holder.t1;
        t1.setText(marke.name);

        // Hier den layoutmanager für den child-rv erstellen
        LinearLayoutManager gm = new LinearLayoutManager(holder.recv.getContext());
        gm.setOrientation(RecyclerView.HORIZONTAL);

        // Hier den Adapter für den child-rv erstellen und die daten übergeben
        EisAdapter eisAdapter = new EisAdapter(marke.sorten);

        // Die erstellten sachen setzen, auch den viewpool
        holder.recv.setLayoutManager(gm);
        holder.recv.setAdapter(eisAdapter);
        holder.recv.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {

        return datalist.size();
    }
}
