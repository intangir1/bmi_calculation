package com.likhtman.bmi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.likhtman.bmi.Callbacks.FragmentCallbacks;
import com.likhtman.bmi.Fragments.CalculationFragment;
import com.likhtman.bmi.Fragments.ResultFragment;
import com.likhtman.bmi.R;

public class MainActivity extends AppCompatActivity implements FragmentCallbacks {


    private CalculationFragment calculationFragment;
    private ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCalculationFragment();
    }


    private void setCalculationFragment() {
        calculationFragment = new CalculationFragment();
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.FrameLayoutContainer, calculationFragment, "calculationFragment").
                commit();
    }


    private void setResultFragment(float result) {
        Bundle bundle = new Bundle();
        bundle.putFloat("result", result);
        resultFragment = new ResultFragment();
        resultFragment.setArguments(bundle);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.FrameLayoutContainer, resultFragment, "resultFragment").
                commit();
    }

    @Override
    public void Change_fragment(float result) {
        if (getSupportFragmentManager().findFragmentByTag("calculationFragment") != null)
            setResultFragment(result);
        else
            setCalculationFragment();
    }
}
