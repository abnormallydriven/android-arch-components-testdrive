package com.abnormallydriven.architecturecomponentspost.userlist;


import android.arch.lifecycle.LifecycleRegistry;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class UserListActivityModule {

    @Provides
    static LifecycleRegistry provideLifecycleRegistry(UserListActivity userListActivity){
        return new LifecycleRegistry(userListActivity);
    }

    @Provides
    static LinearLayoutManager provideLinearLayoutManager(UserListActivity userListActivity){
        return new LinearLayoutManager(userListActivity, LinearLayoutManager.VERTICAL, false);
    }

}
