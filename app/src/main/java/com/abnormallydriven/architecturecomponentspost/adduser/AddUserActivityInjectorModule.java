package com.abnormallydriven.architecturecomponentspost.adduser;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

//makes this a subcomponent of whatever component the module is installed in.
@Module(subcomponents = AddUserActivitySubcomponent.class)
public abstract class AddUserActivityInjectorModule {

    @Binds
    @IntoMap
    @ActivityKey(AddUserActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAddUserActivityInjectorFactory(AddUserActivitySubcomponent.Builder builder);

}
