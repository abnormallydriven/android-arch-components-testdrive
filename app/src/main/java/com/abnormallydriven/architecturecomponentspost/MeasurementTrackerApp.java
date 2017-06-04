package com.abnormallydriven.architecturecomponentspost;


import android.app.Application;

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
