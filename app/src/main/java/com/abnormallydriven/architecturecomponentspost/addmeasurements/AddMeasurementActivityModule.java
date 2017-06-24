package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import android.arch.lifecycle.LifecycleRegistry;

import dagger.Module;
import dagger.Provides;

@Module
class AddMeasurementActivityModule {

    @Provides
    static LifecycleRegistry provideLifecycleRegistry(AddMeasurementsActivity activity){
        return new LifecycleRegistry(activity);
    }


}
