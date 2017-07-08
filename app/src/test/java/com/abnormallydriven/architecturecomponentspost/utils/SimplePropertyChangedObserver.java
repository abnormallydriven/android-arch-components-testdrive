package com.abnormallydriven.architecturecomponentspost.utils;


import android.databinding.Observable;

public class SimplePropertyChangedObserver extends Observable.OnPropertyChangedCallback {

    public boolean propertyDidChange = false;

    @Override
    public void onPropertyChanged(Observable observable, int i) {
        propertyDidChange = true;
    }
}
