package com.example.eisapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.eisapp.model.AddEisFragment;
import com.example.eisapp.model.FinishFragment;
import com.example.eisapp.model.MarkenManager;
import com.example.eisapp.model.Economy;
import com.example.eisapp.model.Eis;
import com.example.eisapp.model.MarkenAdapter;
import com.example.eisapp.model.MenuFragment;
import com.example.eisapp.model.PaymentHandler;
import com.example.eisapp.model.SaleFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Daten von Model per Observer übergeben
    Economy eco;
    PaymentHandler pay;
    List<Eis> eises;
    MarkenAdapter ma;
       // TODO: Fragment in mainactivity +xml einbauen, recyclerview ins fragment
    // TODO: NavigationDrawer einbauen! (Evtl neues Projekt und kopieren ?)


    // Für die den Child-rv
    GridLayoutManager layoutManager;
    TextView teis;
    MarkenManager markenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teis = findViewById(R.id.textVieweis);
        System.out.println(teis);





        markenManager = MarkenManager.getInstance(this);
       // markenManager.fillWithExampleData();
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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
      // Nur ein Menuitem, deshalb keine Fallunterscheidung



                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment tempfrag = getSupportFragmentManager().findFragmentByTag("menufrag");
                if(tempfrag != null){
                    // Schließen
                    fragmentTransaction.remove(tempfrag);

                }else{
                    // Öffnen
                    MenuFragment menuFragment = new MenuFragment();
                    fragmentTransaction.add(R.id.framemenu,menuFragment,"menufrag");


                }


                fragmentTransaction.commit();


        return true;
    }


// Das vielleicht dann nicht ins fragment ?!?
    public void onClickEis(View view){
        TextView t = (TextView) view;
        System.out.println(t.getText());
       eco.addSoldIce( markenManager.getEisByName((String) t.getText()));


        eco.printCurr();
    }

}