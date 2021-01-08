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
import android.widget.ImageView;
import android.widget.TextView;

import com.likhtman.bmi.Callbacks.FragmentCallbacks;
import com.likhtman.bmi.R;


public class ResultFragment extends Fragment {
    Activity parent_activity;
    float result;

    Button btn_back;
    TextView txt_result;
    ImageView img_current_state;

    public ResultFragment() {

    }


    private void initVisuals(View view){
        btn_back = (Button)view.findViewById(R.id.btn_back);
        txt_result = (TextView) view.findViewById(R.id.txt_result);
        img_current_state = (ImageView)view.findViewById(R.id.img_current_state);
    }


    private void Set_picture(){
        if (result < 25)
            img_current_state.setImageResource(R.drawable.one);
        else if (result >= 30)
            img_current_state.setImageResource(R.drawable.three);
        else
            img_current_state.setImageResource(R.drawable.two);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parent_activity = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVisuals(view);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ( (FragmentCallbacks) parent_activity).Change_fragment(-1);
            }
        });

        Bundle bundle = this.getArguments();
        try {
            result = bundle.getFloat("result");
            txt_result.setText(result+"");
        }
        catch (Exception e){

        }

        Set_picture();
    }
}
