package com.abnormallydriven.architecturecomponentspost;


import android.app.Activity;
import android.app.Application;
import android.os.StrictMode;

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
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate();
        initializeDaggerInjector();
        DaggerInjector.getAppComponent().inject(this);
    }

    protected void initializeDaggerInjector() {
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
            .applicationContext(this)
            .build();

        DaggerInjector.initializeComponent(applicationComponent);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
