package com.abnormallydriven.architecturecomponentspost.usermeasurements;


import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = UserMeasurementActivityModule.class)
public interface UserMeasurementActivitySubcomponent extends AndroidInjector<UserMeasurementsActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserMeasurementsActivity>{}
}
