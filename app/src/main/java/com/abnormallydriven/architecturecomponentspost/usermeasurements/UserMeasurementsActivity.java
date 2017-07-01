package com.abnormallydriven.architecturecomponentspost.usermeasurements;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abnormallydriven.architecturecomponentspost.R;

import javax.inject.Inject;

public class UserMeasurementsActivity extends AppCompatActivity implements LifecycleOwner{

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_measurements);
    }

    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
