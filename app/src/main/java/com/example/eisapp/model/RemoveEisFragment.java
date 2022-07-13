package com.example.eisapp.model;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

public class RemoveEisFragment extends Fragment {


    public RecyclerView recyclerView;
    public SaleMarkenAdapter markenAdapter;
    public ConstraintLayout cl;
    public TextView tv;

    public RemoveEisFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.salefragmentlayout, container, false);
        //recyclerView = (RecyclerView) view.findViewById(R.id.recv);






        // Hier kommt im endeffekt der ocl her!


        // Muss das überhaupt hier sein ? reicht doch eig dass das in der xml drin ist oder ? - zum zugriff darauf nicht!
        recyclerView = (RecyclerView) view.findViewById(R.id.recv);


        //Null: es passiert überall dasselbe, was im eisadapter gerschrieben steht
        markenAdapter = new SaleMarkenAdapter(MarkenManager.Instance.marken, new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                // TODO: Zwischenabfrage: Sicher ?!?
                // TODO: Bisschen weniger umständlich bitte

               Eis removeeis = MarkenManager.getInstance(view.getContext()).getEisByName((String) ((TextView)view).getText());
               System.out.print("Remove following: " + removeeis.name);
               Marke mark = MarkenManager.getInstance(view.getContext()).getMarkeByEis(removeeis);
               System.out.println(" Of brand: " + mark.name);
               mark.removeEis(removeeis);
               markenAdapter.notifyDataSetChanged();
               System.out.println(" -> removed!");

            }
        },Color.parseColor("#CCE6FF"));

        recyclerView.setAdapter(markenAdapter);
        recyclerView.setHasFixedSize(true);



        //recyclerView.getChildViewHolder(ll);



        // findViewHolderForItemID ?
        // GetChildViewHolder!?
       // recyclerView.getChildViewHolder();

       //SaleMarkenAdapter mat = (SaleMarkenAdapter) recyclerView.getAdapter();


       // setOnBinfV eiwHodler: dann neue mehtode?!?

        //Layoutmanager setzen parent
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
       
        return view;
    }


// TODO: Nimm den Recv vom verkauf exakt nochmal, aber ändere die onClick-Funktion der Items an der richtigen stelle!



}
