package com.abnormallydriven.architecturecomponentspost.common;

import android.databinding.BindingConversion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BindingConverters {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

    @BindingConversion
    public static String convertDate(Date date){
        return SIMPLE_DATE_FORMAT.format(date);
    }

    @BindingConversion
    public static String convertFloat(float someFloat){
        return String.valueOf(someFloat);
    }

}
