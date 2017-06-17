package com.abnormallydriven.architecturecomponentspost.userlist;

import android.app.Activity;
import android.arch.lifecycle.LifecycleRegistry;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = UserListActivitySubcomponent.class)//makes this a subcomponent of whatever component the module is installed in.
public abstract class UserListActivityInjectorModule {

    @Binds
    @IntoMap
    @ActivityKey(UserListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindUserListActivityInjectorFactory(UserListActivitySubcomponent.Builder builder);

}
