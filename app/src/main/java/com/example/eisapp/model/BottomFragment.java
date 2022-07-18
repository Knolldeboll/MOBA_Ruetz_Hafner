package com.example.eisapp.model;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eisapp.R;

public class BottomFragment extends Fragment {

    public static EditText totalInput;
    public static EditText givenInput;
    public static EditText wTipInput;
    public static EditText changeResult;

    private float totalWTip = 0.00f;
    private float given = 0.00f;
    private float change = 0.00f;

    private Economy economy = Economy.getInstance();

    public BottomFragment() {

    }

    public static BottomFragment newInstance(String param1, String param2) {
        BottomFragment fragment = new BottomFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        totalInput = (EditText) view.findViewById(R.id.TotalInput1);
        givenInput = (EditText) view.findViewById(R.id.givenInput);
        wTipInput = (EditText) view.findViewById(R.id.WTipInput);
        changeResult = (EditText) view.findViewById(R.id.changeResult);

        totalInput.setText(Float.toString(economy.getCurrentValue()) + "€");
        wTipInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {

                    // Summe mit Trinkgeld
                    totalWTip = Float.parseFloat(charSequence.toString());
                    if (totalWTip > 0 && given > 0 && economy.getCurrentValue() > 0) {


                        change = given - totalWTip;


                        changeResult.setText(String.format("%.2f", change) + "€");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        wTipInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    givenInput.requestFocus();

                    return true;
                }
                return false;
            }
        });

        wTipInput.requestFocus();
        toggleKeyboard();

        givenInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {

                    given = Float.parseFloat(charSequence.toString());
                    if (totalWTip > 0 && given > 0 && economy.getCurrentValue() > 0) {

                        change = Math.round((given - totalWTip) * 100f) / 100f;
                        changeResult.setText(String.format("%.2f", change) + "€");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    public boolean checkout() {

        if (given != 0.00f && given >= totalWTip && totalWTip >= Economy.getInstance().getCurrentValue()) {

            given = Math.round(given * 100f) / 100f;
            Economy.getInstance().dailyIncome += Math.round((totalWTip) * 100f) / 100f;
            Economy.getInstance().finishCurrentSale();
            toggleKeyboard();
            return true;

        } else {

            if (Economy.getInstance().getCurrentValue() != 0) {
                Toast.makeText(this.getContext(), "Zu wenig gegeben!", Toast.LENGTH_SHORT).show();
            }

            return false;

        }

    }

    private void toggleKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, 0);
    }
}