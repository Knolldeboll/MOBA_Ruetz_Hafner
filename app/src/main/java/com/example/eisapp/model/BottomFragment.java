package com.example.eisapp.model;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eisapp.R;

public class BottomFragment extends Fragment {

    public static EditText totalInput;
    public static EditText givenInput;
    public static EditText wTipInput;
    public static TextView changeResult;

    private float total = 0.00f;
    private float totalWTip = 0.00f;
    private float given  = 0.00f;
    private float change = 0.00f;

    public BottomFragment() {

    }

    // TODO: Rename and change types and number of parameters
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
        View view  = inflater.inflate(R.layout.fragment_bottom, container, false);

        totalInput = (EditText) view.findViewById(R.id.TotalInput1);
        givenInput = (EditText) view.findViewById(R.id.givenInput);
        wTipInput = (EditText) view.findViewById(R.id.WTipInput);
        changeResult = (TextView) view.findViewById(R.id.changeResult);

        totalInput.setText(Float.toString(total));

        // Inflate the layout for this fragment
        return view;
    }
}