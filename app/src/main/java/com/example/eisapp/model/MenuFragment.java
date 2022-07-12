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

    public Button b1;
    public Button b2;
    public Button b3;
    public Button b4;

    public MenuFragment(){

    }
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



    View view = inflater.inflate(R.layout.menufragmentlayout, container, false);

    b1 = (Button) view.findViewById(R.id.abschlussbutton);
    b2 = (Button) view.findViewById(R.id.neueisbutton);
    b3 = (Button) view.findViewById(R.id.homebutton);
    b4 = (Button) view.findViewById(R.id.deletebutton);

    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {



            FinishFragment ff = new FinishFragment();
            FragmentTransaction fragmentTransaction1 = getParentFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.framemain,ff,"finishfrag");
            //fragmentTransaction1.remove(R.id.)
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

    b4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RemoveEisFragment removeEisFragment = new RemoveEisFragment();
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framemain,removeEisFragment,"removeeisfragment");
            fragmentTransaction.commit();
        }
    });







    return view;
}

}
