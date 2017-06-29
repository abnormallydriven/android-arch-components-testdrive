package com.abnormallydriven.architecturecomponentspost.common;

import android.databinding.InverseMethod;
import android.widget.EditText;

public class BindingAdapters {


    @InverseMethod("getFloat")
    public static String convertFloatToString(EditText editText, float floatValue){
        return String.valueOf(floatValue);
    }


    public static float getFloat(EditText editText, String newValue){
        return Float.parseFloat(newValue);
    }
}
