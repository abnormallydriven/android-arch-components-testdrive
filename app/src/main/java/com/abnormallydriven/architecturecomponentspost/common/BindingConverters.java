package com.abnormallydriven.architecturecomponentspost.common;

import android.databinding.BindingConversion;
import android.databinding.ObservableFloat;

public class BindingConverters {


    @BindingConversion
    public static String convertFloatToString(ObservableFloat observableFloat){
        return String.valueOf(observableFloat.get());
    }
}
