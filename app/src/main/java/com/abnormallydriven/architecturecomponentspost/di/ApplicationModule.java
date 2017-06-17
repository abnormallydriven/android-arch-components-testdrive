package com.abnormallydriven.architecturecomponentspost.di;


import android.app.Application;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import com.abnormallydriven.architecturecomponentspost.common.MainThreadExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ExecutorsModule.class)
public class ApplicationModule {

    @Provides
    @Singleton
    static Resources provideResources(Application application){
        return application.getResources();
    }

    @Provides
    @Singleton
    @UI
    public Handler provideUiHandler(){
        return new Handler(Looper.getMainLooper());
    }

}
