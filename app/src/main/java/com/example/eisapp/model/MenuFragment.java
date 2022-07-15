package com.example.eisapp.model;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
    public Button[] buttons;
    public static View savedView;

    public MenuFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //View als Singleton speichern, um die States der Buttons zu restoren
        if (savedView == null) {
            savedView = inflater.inflate(R.layout.menufragmentlayout, container, false);
        }


        // Wenn der eine noch nicht initialisiert wurde sind die anderen auch nicht da. Ansonsten behalte die Buttons.
        //b1 = (Button) view.findViewById(R.id.abschlussbutton);


        b1 = (Button) savedView.findViewById(R.id.abschlussbutton);
        b2 = (Button) savedView.findViewById(R.id.neueisbutton);
        b3 = (Button) savedView.findViewById(R.id.homebutton);
        b4 = (Button) savedView.findViewById(R.id.deletebutton);

        buttons = new Button[]{b1, b2, b3, b4};

        //Abschluss
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!checkOpenSale()) {


                    System.out.println("CLick b1");
                    disableButton(b1);
                    enableButtonsExcept(b1);

                    getActivity().onOptionsItemSelected(null);

                    FinishFragment ff = new FinishFragment();
                    FragmentTransaction fragmentTransaction1 = getParentFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.framemain, ff, "finishfrag");
                    //fragmentTransaction1.remove(R.id.)
                    fragmentTransaction1.commit();


                /*   markenManager.removeBrand(markenManager.marken.get(markenManager.marken.size()-1));
                ma.notifyItemRemoved(ma.getItemCount());
            */

                }

            }
        });
        // Neueis
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkOpenSale()) {


                    System.out.println("CLick b2");
                    disableButton(b2);
                    enableButtonsExcept(b2);

                    getActivity().onOptionsItemSelected(null);
                    AddEisFragment aef = new AddEisFragment();
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framemain, aef, "addeisfrag");
                    fragmentTransaction.commit();
                }


            }
        });

        // Sale
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("CLick b3");
                disableButton(b3);
                enableButtonsExcept(b3);

                getActivity().onOptionsItemSelected(null);
                SaleFragment saleFragment = new SaleFragment();
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framemain, saleFragment, "salefrag");
                fragmentTransaction.commit();


            }
        });

        // Delete
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CLick b4");

                if (!checkOpenSale()) {

                    disableButton(b4);
                    enableButtonsExcept(b4);

                    getActivity().onOptionsItemSelected(null);
                    RemoveEisFragment removeEisFragment = new RemoveEisFragment();
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framemain, removeEisFragment, "removeeisfragment");
                    fragmentTransaction.commit();
                }

            }
        });


        System.out.println("MEnu onCreateView Buttons: " + b1.hasOnClickListeners() + b2.hasOnClickListeners() + b3.hasOnClickListeners() + b4.hasOnClickListeners());
        System.out.println("MEnu onCreateView Buttons enabled: " + b1.isEnabled() + b2.isEnabled() + b3.isEnabled() + b4.isEnabled());


        return savedView;
    }

    public void disableButton(Button b) {
        b.setEnabled(false);
        b.setBackgroundColor(Color.GRAY);
    }

    public void enableButtonsExcept(Button b) {

        for (Button button : buttons) {
            if (button != b) {
                button.setEnabled(true);
                button.setBackgroundColor(Color.parseColor("#3F51B5"));

            }
        }

    }

    private boolean checkOpenSale() {
        if (Economy.Instance.getCurrentValue() != 0) {
            Toast.makeText(this.getContext(), "Offener Verkauf! Erst beenden!", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }


}
