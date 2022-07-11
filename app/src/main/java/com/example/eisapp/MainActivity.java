package com.example.eisapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eisapp.model.BottomFragment;
import com.example.eisapp.model.Marke;
import com.example.eisapp.model.MarkenManager;
import com.example.eisapp.model.Economy;
import com.example.eisapp.model.Eis;
import com.example.eisapp.model.MarkenAdapter;
import com.example.eisapp.model.PaymentHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Daten von Model per Observer übergeben
    Economy eco;
    PaymentHandler pay;
    List<Eis> eises;
    MarkenAdapter ma;
    Button b;
    GridLayoutManager layoutManager;
    TextView teis;
    MarkenManager markenManager;

    ImageButton payButton;
    TextView totalText;
    BottomFragment bottomFragment;
    FragmentTransaction transaction;

    boolean payViewOpen = false;

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

        RecyclerView rv = (RecyclerView) findViewById(R.id.recv);


        ma = new MarkenAdapter(markenManager.marken);

        //Dummydaten erzeugen
        //eises.add(new Eis("popel"));
        /*eises.add(new Eis("schoko"));
        eises.add(new Eis("erdbeer"));
        eises.add(new Eis("derdbeer"));
        eises.add(new Eis("ferdbeer"));
        eises.add(new Eis("rerdbeer"));
        eises.add(new Eis("kerdbeer"));
        eises.add(new Eis("perdbeer"));
        eises.add(new Eis("äerdbeer"));
        */
        // Rv holen


        // Adapter für die daten erzeugen



        // beim rv den adapter setzen


        // IDEE FÜR DYNAMISCHE Unterlisten pro marken-"Item" : Recyclerview im recyclerview, oder einfach gridview darin
        rv.setAdapter(ma);

        rv.setHasFixedSize(true);

        //Layoutmanager setzen
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Für den child-rv:
        layoutManager= new GridLayoutManager(this,3);


        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               markenManager.removeBrand(markenManager.marken.get(markenManager.marken.size()-1));
                ma.notifyItemRemoved(ma.getItemCount());

            }
        });




        // addSoldIce wird dann von den ActionHandlern der Buttons aufgerufen
        //Dies alles ist nur zum testen

        // Eis adden blablabla

        //Dann : press auf zahlen!

        //pay.currentSum = eco.getCurrentValue();

        // TODO: Das Layout vom Parentrv so machen, dass sich die größe an die kinder anpasst!

        Marke m = new Marke("Langneso");
        m.addEis(new Eis("Vanillollololol",2.0f));
        m.addEis(new Eis("Vanillolol",2.0f));
        m.addEis(new Eis("Vanillollool",2.0f));
        m.addEis(new Eis("Vanllolol",2.0f));
        m.addEis(new Eis("Erde",2.0f));

        markenManager.addBrand(m);
        //eco.printDay();
       /* markenManager.removeBrand(markenManager.marken.get(3));
        markenManager.removeBrand(markenManager.marken.get(4));
        markenManager.removeBrand(markenManager.marken.get(5));
*/
        eco.addSoldIce(markenManager.marken.get(1).sorten.get(1));

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


        //TODO: PayButton farbe zu weiß ändern
        payButton = (ImageButton) findViewById(R.id.payButton);
        totalText = (TextView) findViewById(R.id.totalText);
        totalText.setText("Gesamt: " + Float.toString(eco.getCurrentValue()) + "€");
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(payViewOpen) {
                    bottomFragment.checkout();
                    Fragment tmpFrag = getSupportFragmentManager().findFragmentByTag("bottomFragment");
                    if (tmpFrag != null) {
                        transaction = getSupportFragmentManager().beginTransaction();
                    }
                    displayPayImage();
                    payViewOpen = false;
                } else {
                    System.out.println("Starting bottom fragment");
                    bottomFragment = new BottomFragment();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.framemain, bottomFragment, "bottomFragment");
                    transaction.commit();
                    displayCheckImage();
                    payViewOpen = true;
                }
            }
        });
    }

    public void displayCheckImage() {
        payButton.setImageResource(R.mipmap.check);
    }

    public void displayPayImage() {
        payButton.setImageResource(R.mipmap.pay);
    }

    public void onClickEis(View view){
        TextView t = (TextView) view;
        System.out.println(t.getText());
       eco.addSoldIce( markenManager.getEisByName((String) t.getText()));

        eco.printCurr();
    }

}