package com.abnormallydriven.architecturecomponentspost;


import android.app.Activity;
import android.app.Application;

import com.abnormallydriven.architecturecomponentspost.common.di.ApplicationComponent;
import com.abnormallydriven.architecturecomponentspost.common.di.DaggerApplicationComponent;
import com.abnormallydriven.architecturecomponentspost.common.di.DaggerInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MeasurementTrackerApp extends Application  implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
            .applicationContext(this)
            .build();
        
        DaggerInjector.initializeComponent(applicationComponent);
        DaggerInjector.getAppComponent().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
