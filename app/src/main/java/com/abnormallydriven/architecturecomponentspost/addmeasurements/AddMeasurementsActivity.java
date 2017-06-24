package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abnormallydriven.architecturecomponentspost.R;

public class AddMeasurementsActivity extends AppCompatActivity implements LifecycleOwner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurements);
    }

    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
