package com.example.eisapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.eisapp.model.Fragments.BottomFragment;

import com.example.eisapp.model.MarkenManager;
import com.example.eisapp.model.Economy;
import com.example.eisapp.model.Eis;
import com.example.eisapp.model.Fragments.OverviewFragment;
import com.example.eisapp.model.Fragments.MenuFragment;
import com.example.eisapp.model.Fragments.SaleFragment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {


    Economy eco;
    TextView teis;
    MarkenManager markenManager;
    public static boolean save = true;

    ImageButton payButton;
    ImageButton undoButton;
    TextView totalText;
    BottomFragment bottomFragment;
    OverviewFragment overviewFragment;
    FragmentTransaction transaction;

    boolean payViewOpen = false;
    boolean overviewOpen = false;
    public static Eis lastEis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            FileInputStream fis = this.openFileInput("economy.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Economy e = (Economy) ois.readObject();
            if (e != null) {

                Economy.Instance = e;

            }


        } catch (FileNotFoundException e) {

            // Noch keine Datei da! Ist bei erstem Start aber normal und ok!
            e.printStackTrace();
        } catch (IOException e) {


            e.printStackTrace();
        } catch (ClassNotFoundException e) {


            e.printStackTrace();
        }


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_color)));


        teis = findViewById(R.id.textVieweis);


        markenManager = MarkenManager.getInstance(this);


        eco = Economy.getInstance();

        SaleFragment saleFragment = new SaleFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framemain, saleFragment, "salefrag");
        fragmentTransaction.commit();

        // Zu Testzwecken:
        //MarkenManager.Instance.fillWithExampleData();


        payButton = (ImageButton) findViewById(R.id.payButton);
        totalText = (TextView) findViewById(R.id.totalText);
        undoButton = (ImageButton) findViewById(R.id.undoButton);
        totalText.setText(String.format("%.2f", eco.getCurrentValue()) + "€");
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Economy.getInstance().getCurrentValue() == 0) {

                    Toast.makeText(view.getContext(), "Noch nichts zum Verkauf hinzugefügt!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (payViewOpen) {
                    Fragment tmpFrag = getSupportFragmentManager().findFragmentByTag("bottomFragment");
                    if (tmpFrag != null) {
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, 0, 0);
                        transaction.remove(tmpFrag);
                        transaction.commit();
                    }
                    displayUndoImage();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, 0);
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
                    totalText.setText(String.format("%.2f", eco.getCurrentValue()) + "€");
                    lastEis = null;
                }
                if (payViewOpen) {
                    if (!bottomFragment.checkout()) {
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
                }
            }
        });

        totalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!overviewOpen) {

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


    protected void onStop() {

        super.onStop();
        System.out.println("ONSTOP");


        try {
            FileOutputStream fos = this.openFileOutput("economy.txt", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            if (save) {
                oos.writeObject(Economy.getInstance());
            } else {

                oos.writeObject(null);
            }

            oos.close();
            fos.close();
            System.out.println("Saved economy to file!");


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }


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