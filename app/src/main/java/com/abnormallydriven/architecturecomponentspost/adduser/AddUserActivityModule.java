package com.abnormallydriven.architecturecomponentspost.adduser;

import android.arch.lifecycle.LifecycleRegistry;

import dagger.Module;
import dagger.Provides;

@Module
class AddUserActivityModule {

    @Provides
    static LifecycleRegistry provideLifecycleRegistry(AddUserActivity addUserActivity){
        return new LifecycleRegistry(addUserActivity);
    }

}
