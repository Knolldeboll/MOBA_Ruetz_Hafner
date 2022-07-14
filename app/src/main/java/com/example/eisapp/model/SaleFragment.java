package com.example.eisapp.model;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

public class SaleFragment extends Fragment {

    public static RecyclerView recyclerView;
    public static SaleMarkenAdapter markenAdapter;
    public static GridLayoutManager childLayout;

    public SaleFragment(){

    }

    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // TODO: Anzahl verkaufte
        // IDEE: Beim childrecycler: GridLayout wie gewollt, aber beim parent: Wrap content!?? Sodass bei zb drei spalten bei mehreren
        // Kein umbruch entsteht und nicht gescrollt werden muss ?

        // TODO: Anzahl geklickte eis anzeigen (Nummer)
View view = inflater.inflate(R.layout.salefragmentlayout, container, false);

// init stuff
        // Auf view zugreifen!
        // Muss das überhaupt hier sein ? reicht doch eig dass das in der xml drin ist oder ?
        recyclerView = (RecyclerView) view.findViewById(R.id.recv);

        // TODO: OCL fürs sale mitegeben!
        markenAdapter = new SaleMarkenAdapter(MarkenManager.Instance.marken,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //formerly onClickEis() in main ;
                TextView temptv = (TextView)view;
                System.out.println(temptv.getText());
                Economy.getInstance().addSoldIce( MarkenManager.getInstance(view.getContext()).getEisByName((String)temptv.getText()));

                //TODO: Fix TextView total, evtl als singleton
                TextView totaltext = (TextView)view.findViewById(R.id.totalText);
                if(totaltext!= null){
                    totaltext.setText(String.valueOf(Economy.getInstance().getCurrentValue()));
                }

                Economy.getInstance().printCurr();
            }
        }, Color.parseColor("#F3CCCC"));

        recyclerView.setAdapter(markenAdapter);
        recyclerView.setHasFixedSize(true);

        //Layoutmanager setzen parent
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        childLayout= new GridLayoutManager(view.getContext(),3);





return view;
    }

}
