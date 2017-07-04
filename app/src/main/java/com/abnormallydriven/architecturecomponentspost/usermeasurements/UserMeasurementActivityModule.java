package com.abnormallydriven.architecturecomponentspost.usermeasurements;


import android.arch.lifecycle.LifecycleRegistry;
import android.support.v7.widget.LinearLayoutManager;

import com.abnormallydriven.architecturecomponentspost.userlist.UserListActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class UserMeasurementActivityModule {

    @Provides
    static LifecycleRegistry provideLifecycleRegistry(UserMeasurementsActivity activity){
        return new LifecycleRegistry(activity);
    }

    @Provides
    static LinearLayoutManager provideLinearLayoutManager(UserMeasurementsActivity activity){
        return new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
    }

}
