package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

//makes this a subcomponent of whatever component the module is installed in.
@Module(subcomponents = AddMeasurementActivitySubcomponent.class)
public abstract class AddMeasurementsActivityInjectorModule {

    @Binds
    @IntoMap
    @ActivityKey(AddMeasurementsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAddMeasurementsActivity(AddMeasurementActivitySubcomponent.Builder builder);

}
