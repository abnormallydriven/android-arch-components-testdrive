package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = AddMeasurementActivityModule.class)
public interface AddMeasurementActivitySubcomponent extends AndroidInjector<AddMeasurementsActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AddMeasurementsActivity>{}

}
