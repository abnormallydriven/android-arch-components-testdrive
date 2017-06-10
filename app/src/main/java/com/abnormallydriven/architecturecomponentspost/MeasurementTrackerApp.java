package com.abnormallydriven.architecturecomponentspost;


import android.app.Application;

import com.abnormallydriven.architecturecomponentspost.di.ApplicationComponent;
import com.abnormallydriven.architecturecomponentspost.di.DaggerApplicationComponent;
import com.abnormallydriven.architecturecomponentspost.di.DaggerInjector;

public class MeasurementTrackerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
            .applicationContext(this)
            .build();
        
        DaggerInjector.initializeComponent(applicationComponent);
    }
}
