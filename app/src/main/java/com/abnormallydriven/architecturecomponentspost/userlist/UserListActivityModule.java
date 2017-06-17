package com.abnormallydriven.architecturecomponentspost.userlist;


import android.arch.lifecycle.LifecycleRegistry;

import dagger.Module;
import dagger.Provides;

@Module
public class UserListActivityModule {

    @Provides
    static LifecycleRegistry provideLifecycleRegistry(UserListActivity userListActivity){
        return new LifecycleRegistry(userListActivity);
    }

}
