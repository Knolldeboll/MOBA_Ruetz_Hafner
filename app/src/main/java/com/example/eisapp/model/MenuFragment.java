package com.example.eisapp.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eisapp.R;

public class MenuFragment extends Fragment {

    public static Button b1;
    public static Button b2;
    public static Button b3;

    public MenuFragment(){

    }
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    // TODO: Problem! Durchklickbar! Evtl das Salefragment solange disabeln o.ä.

    View view = inflater.inflate(R.layout.menufragmentlayout, container, false);

    b1 = (Button) view.findViewById(R.id.abschlussbutton);
    b2 = (Button) view.findViewById(R.id.neueisbutton);
    b3 = (Button) view.findViewById(R.id.homebutton);

    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // TODO: Die wird von Jojos menü erledigt!

            Economy.getInstance().finishCurrentSale();

            FinishFragment ff = new FinishFragment();
            FragmentTransaction fragmentTransaction1 = getParentFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.framemain,ff,"finishfrag");
            fragmentTransaction1.commit();


                /*   markenManager.removeBrand(markenManager.marken.get(markenManager.marken.size()-1));
                ma.notifyItemRemoved(ma.getItemCount());
            */
        }
    });
    // Neueis
    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AddEisFragment aef = new AddEisFragment();
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framemain, aef, "addeisfrag");
            fragmentTransaction.commit();
        }
    });

    b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SaleFragment saleFragment = new SaleFragment();
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framemain, saleFragment, "salefrag");
            fragmentTransaction.commit();
        }
    });







    return view;
}

}
