package com.example.eisapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import android.widget.ImageButton;

import com.example.eisapp.model.BottomFragment;

import com.example.eisapp.model.MarkenManager;
import com.example.eisapp.model.Economy;
import com.example.eisapp.model.Eis;
import com.example.eisapp.model.OverviewFragment;
import com.example.eisapp.model.SaleMarkenAdapter;
import com.example.eisapp.model.MenuFragment;
import com.example.eisapp.model.PaymentHandler;
import com.example.eisapp.model.SaleFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Daten von Model per Observer übergeben
    Economy eco;
    PaymentHandler pay;
    List<Eis> eises;



//  TODO: Einheitliches design!

    TextView teis;
    MarkenManager markenManager;

    ImageButton payButton;
    ImageButton undoButton;
    TextView totalText;
    BottomFragment bottomFragment;
    OverviewFragment overviewFragment;
    FragmentTransaction transaction;

    boolean payViewOpen = false;
    boolean overviewOpen = false;
    Eis lastEis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teis = findViewById(R.id.textVieweis);
        System.out.println(teis);

        markenManager = MarkenManager.getInstance(this);
        //markenManager.fillWithExampleData();
        markenManager.printList();

        eco = Economy.getInstance();
        pay = new PaymentHandler();


        SaleFragment saleFragment = new SaleFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framemain, saleFragment, "salefrag");
        fragmentTransaction.commit();


        //Dann : press auf zahlen!

        //pay.currentSum = eco.getCurrentValue();

        /*
        Marke m = new Marke("Langneso");
        m.addEis(new Eis("Vanillollololol",2.0f));
        m.addEis(new Eis("Vanillolol",2.0f));
        m.addEis(new Eis("Vanillollool",2.0f));
        m.addEis(new Eis("Vanllolol",2.0f));
        m.addEis(new Eis("Erde",2.0f));

        markenManager.addBrand(m);*/


        //eco.printDay();
       /* markenManager.removeBrand(markenManager.marken.get(3));
        markenManager.removeBrand(markenManager.marken.get(4));
        markenManager.removeBrand(markenManager.marken.get(5));
*/
        //MarkenManager.Instance.fillWithExampleData();

        pay.currentSum = eco.getCurrentValue();

        // Wenn summenfeld geändert: ändere auch currentvalue im pay
        //pay.currentSum = inputfieldsum.value oder so
        //Tagestrinkgeld errechnet sich dann aus daylist.getsum - dailysum

        //gegeben entern!
        // on enter:
        //pay.getChange(inputfieldgegeben.value)
        //System.out.println(pay.getChange(50));


        // Wenn <0: nochmal! zu wenig gegeben!

        // Wenn >=0 : anzeige im rückgeldfeld
        //Dann : pay

        //pay.pay();

        eco.printDay();


        payButton = (ImageButton) findViewById(R.id.payButton);
        totalText = (TextView) findViewById(R.id.totalText);
        undoButton = (ImageButton) findViewById(R.id.undoButton);
        totalText.setText("Gesamt: " + Float.toString(eco.getCurrentValue()) + "€");
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (payViewOpen) {
                    if(!bottomFragment.checkout()){
                        return;
                    }

                    totalText.setText("Gesamt: 0€");
                    Fragment tmpFrag = getSupportFragmentManager().findFragmentByTag("bottomFragment");
                    if (tmpFrag != null) {
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, 0, 0);
                        transaction.remove(tmpFrag);
                        transaction.commit();
                    }
                    displayUndoImage();
                    payViewOpen = false;
                } else {
                    if (overviewOpen) {
                        Fragment tmpFrag = getSupportFragmentManager().findFragmentByTag("overviewFragment");
                        if (tmpFrag != null) {
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, 0, 0);
                            transaction.remove(tmpFrag);
                            transaction.commit();
                            overviewOpen = false;
                        }
                    }
                    bottomFragment = new BottomFragment();
                    transaction = getSupportFragmentManager().beginTransaction();

                    transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, 0, 0);
                    transaction.add(R.id.framemain, bottomFragment, "bottomFragment");

                    transaction.commit();
                    displayCheckImage();
                    payViewOpen = true;
                }
            }
        });
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!payViewOpen && lastEis != null) {
                    eco.removeSoldIce(lastEis);
                    totalText.setText("Gesamt: " + eco.getCurrentValue() + "€");
                    lastEis = null;
                }
                if (payViewOpen) {
                    bottomFragment.checkout();
                    totalText.setText("Gesamt: 0€");
                    Fragment tmpFrag = getSupportFragmentManager().findFragmentByTag("bottomFragment");
                    if (tmpFrag != null) {
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, 0, 0);
                        transaction.remove(tmpFrag);
                        transaction.commit();
                    }
                    displayUndoImage();
                    payViewOpen = false;
                }
            }
        });

        totalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!overviewOpen) {
                    overviewFragment = new OverviewFragment();
                    transaction = getSupportFragmentManager().beginTransaction();

                    transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, 0, 0);
                    transaction.add(R.id.framemain, overviewFragment, "overviewFragment");

                    transaction.commit();
                    overviewOpen = true;
                } else {
                    Fragment tmpFrag = getSupportFragmentManager().findFragmentByTag("overviewFragment");
                    if (tmpFrag != null) {
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, 0, 0);
                        transaction.remove(tmpFrag);
                        transaction.commit();
                        overviewOpen = false;
                    }
                }
            }
        });
    }

    public void displayCheckImage() {
        undoButton.setImageResource(R.mipmap.check);
    }

    public void displayUndoImage() {
        undoButton.setImageResource(R.mipmap.undo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Nur ein Menuitem, deshalb keine Fallunterscheidung



        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment tempfrag = getSupportFragmentManager().findFragmentByTag("menufrag");
        if (tempfrag != null) {
            // Schließen
            fragmentTransaction.remove(tempfrag);

        } else {
            // Öffnen
            MenuFragment menuFragment = new MenuFragment();
            fragmentTransaction.add(R.id.framemenu, menuFragment, "menufrag");


        }


        fragmentTransaction.commit();


        return true;
    }

}