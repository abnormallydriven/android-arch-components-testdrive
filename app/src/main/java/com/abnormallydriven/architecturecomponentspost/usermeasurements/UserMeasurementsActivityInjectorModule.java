package com.abnormallydriven.architecturecomponentspost.usermeasurements;


import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = UserMeasurementActivitySubcomponent.class)
public abstract class UserMeasurementsActivityInjectorModule {

    @Binds
    @IntoMap
    @ActivityKey(UserMeasurementsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAddMeasurementsActivity(UserMeasurementActivitySubcomponent.Builder builder);


}
