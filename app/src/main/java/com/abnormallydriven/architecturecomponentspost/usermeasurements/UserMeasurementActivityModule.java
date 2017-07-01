package com.abnormallydriven.architecturecomponentspost.usermeasurements;


import android.arch.lifecycle.LifecycleRegistry;

import dagger.Module;
import dagger.Provides;

@Module
public class UserMeasurementActivityModule {

    @Provides
    static LifecycleRegistry provideLifecycleRegistry(UserMeasurementsActivity activity){
        return new LifecycleRegistry(activity);
    }

}
