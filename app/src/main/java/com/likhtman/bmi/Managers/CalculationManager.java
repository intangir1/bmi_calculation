package com.likhtman.bmi.Managers;

import android.text.TextUtils;

import com.likhtman.bmi.Callbacks.CalculationManagerCallbacks;
import com.likhtman.bmi.Exceptions.FloatExtendedFormatException;
import com.likhtman.bmi.Exceptions.NotNumberException;

public class CalculationManager {
    private String str_result;
    private boolean is_cleaning_screen = false;

    private float mass;
    private float height;
    private boolean is_mass;

    private CalculationManagerCallbacks callbacks;


    public void initDefault(){
        mass = 0;
        height = 0;
        is_mass = true;
        str_result = "";
    }

    public CalculationManager(CalculationManagerCallbacks callbacks){
        this.callbacks = callbacks;
        initDefault();
    }


    public String RecieveCharAndTextReturnText(char to_add, String text){
        if (is_cleaning_screen){
            is_cleaning_screen = false;
            text="";
        }
        str_result = text;
        str_result += to_add;
        return str_result;
    }

    private Exception tryParseFloat(String value) {
        try {
            Float.parseFloat(value);
            return null;
        } catch (NumberFormatException e) {
            if(!TextUtils.isDigitsOnly(value))
                throw new NotNumberException("ERROR - not number");
            else
                throw new FloatExtendedFormatException("ERROR - number extended format");
        }
    }




    private void Change_to_height(){
        is_mass = !is_mass;
        is_cleaning_screen = true;
        callbacks.Change_state(true);
    }


    private void Calculate_result(){
        try{
            float result = mass/(float)(Math.pow((double)height, (double)2));
            callbacks.Pass_result(result);
        } catch (Exception e){
            e.getMessage();
        }
    }


    public String Equal(){
        if(str_result == null || str_result.equals("")){
            is_cleaning_screen = true;
            return ("Error - no input");
        }

        try{
            tryParseFloat(str_result);
            if (is_mass) {
                mass = Float.parseFloat(str_result);
                Change_to_height();
            }
            else {
                height = Float.parseFloat(str_result);
                Calculate_result();
            }
        } catch (NumberFormatException e){
            is_cleaning_screen = true;
            throw new FloatExtendedFormatException("ERROR - float extended");
        }

        str_result = "";
        is_cleaning_screen = true;
        return "";
    }


}
