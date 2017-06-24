package com.abnormallydriven.architecturecomponentspost.common.di;


import com.abnormallydriven.architecturecomponentspost.common.MainThreadExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ExecutorsModule {

    @Provides
    @Singleton
    @UI
    public Executor provideUIExecutor(MainThreadExecutor mainThreadExecutor){
        return mainThreadExecutor;
    }

    @Provides
    @Singleton
    @Network
    public Executor provideNetworkExecutor(){
        return Executors.newFixedThreadPool(3);
    }

    @Provides
    @Singleton
    @Disk
    public Executor provideDiskExecutor(){
        return Executors.newSingleThreadExecutor();
    }

}
