package com.likhtman.bmi.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.likhtman.bmi.Callbacks.CalculationManagerCallbacks;
import com.likhtman.bmi.Callbacks.FragmentCallbacks;
import com.likhtman.bmi.Managers.CalculationManager;
import com.likhtman.bmi.R;


public class CalculationFragment extends Fragment implements CalculationManagerCallbacks, View.OnClickListener {
    private Activity parent_activity;

    private CalculationManager calculationManager;

    private Button btn_zero;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;
    private Button btn_five;
    private Button btn_six;
    private Button btn_seven;
    private Button btn_eight;
    private Button btn_nine;
    private Button btn_enter;
    private Button btn_point;
    private EditText etxt_input;


    private void initVisuals(View view){
        btn_zero = (Button)view.findViewById(R.id.btn_zero);
        btn_one = (Button)view.findViewById(R.id.btn_one);
        btn_two = (Button)view.findViewById(R.id.btn_two);
        btn_three = (Button)view.findViewById(R.id.btn_three);
        btn_four = (Button)view.findViewById(R.id.btn_four);
        btn_five = (Button)view.findViewById(R.id.btn_five);
        btn_six = (Button)view.findViewById(R.id.btn_six);
        btn_seven = (Button)view.findViewById(R.id.btn_seven);
        btn_eight = (Button)view.findViewById(R.id.btn_eight);
        btn_nine = (Button)view.findViewById(R.id.btn_nine);
        btn_point = (Button)view.findViewById(R.id.btn_point);
        btn_enter = (Button)view.findViewById(R.id.btn_enter);

        etxt_input = (EditText)view.findViewById(R.id.etxt_input);
    }


    private void Add_char(char to_add){
        etxt_input.setText(calculationManager.RecieveCharAndTextReturnText(to_add, etxt_input.getText().toString()));
    }


    private void initClickable(){
        btn_zero.setOnClickListener(this);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_enter.setOnClickListener(this);
    }


    public CalculationFragment() {
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parent_activity = getActivity();
        calculationManager = new CalculationManager(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculation, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVisuals(view);
        initClickable();
    }

    @Override
    public void Change_state(boolean to_height) {
        if (to_height) {
            btn_enter.setText(R.string.enter);
            etxt_input.setHint(R.string.enter_your_height_m);
        }
        else {
            btn_enter.setText(R.string.next);
            etxt_input.setHint(R.string.enter_your_mass_kg);
        }

    }

    @Override
    public void Pass_result(float result) {
        btn_enter.setText(R.string.next);
        etxt_input.setHint(R.string.enter_your_mass_kg);
        calculationManager.initDefault();
        ( (FragmentCallbacks) parent_activity).Change_fragment(result);
    }

    @Override
    public void onClick(View view) {
        Button b = (Button)view;
        char buttonText;
        if (!b.getText().toString().equals("Next") && !b.getText().toString().equals("Enter")){
            buttonText = b.getText().toString().charAt(0);
            Add_char(buttonText);
        }
        else{
            etxt_input.setText(calculationManager.Equal());
        }

    }
}
