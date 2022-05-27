package com.example.eisapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

import java.util.List;


public class EisAdapter extends RecyclerView.Adapter<EisAdapter.ViewHolder> {
    private List<Eis> eisliste;


    // Der viewholder stellt struktur zur späteren speicherung der view, wie sie in der xml definiert ist
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView t1;
        public TextView t2;
        public TextView t3;

        public ViewHolder(View itemView){

            // Wie mit variablen anzahlen ?
            // Evtl ein
            super(itemView);

            // Die textviews aus der xml holen
            t1 = (TextView) itemView.findViewById(R.id.textView1);
            t2 = (TextView) itemView.findViewById(R.id.textView2);
            t3 = (TextView) itemView.findViewById(R.id.textView3);
        }

    }

    // Hier werden die anzuzeigenden eise gespiechert

    // Beim erstellen des Objekts die eisliste hier befüllen mit werten von aussen, z.b. aus dem model
    public EisAdapter(List<Eis> eisList){eisliste = eisList; }

    // hier wird beim initialisieren das tatsächliche layout (view) in den viewholder gespeichert!
    @Override
    public EisAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout - dh das xml-layout hier initial anwenden, noch aber leer
        View eisView = inflater.inflate(R.layout.itemlayout, parent, false);

        // Return a new holder instance - hat dann das xmllayout gespeichert und inflated
        ViewHolder viewHolder = new ViewHolder(eisView);
        return viewHolder;
    }

    // Nun daten in den viewholder - wie werden die daten in die view gesetzt ?
    // wird per position gemacht! bzw für alle
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // die anzuzeigenden daten wählen
        Eis eis = eisliste.get(position);
        System.out.println("onBindViewHolder CALLED");
        //hier dann die im viewholder gespeicherten elemente aus dem layout holen und bestücken
        TextView t1 = holder.t1;
        TextView t2 = holder.t2;
        TextView t3 = holder.t3;
        t1.setText(eis.name);
        t2.setText(R.string.preis);
        t3.setText(Float.toString(eis.preis));
    }

    @Override
    public int getItemCount() {

        System.out.println("Anz eise : " + eisliste.size());
        return eisliste.size();
    }
}
