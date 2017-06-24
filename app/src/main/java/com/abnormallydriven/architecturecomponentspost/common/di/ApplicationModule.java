package com.abnormallydriven.architecturecomponentspost.common.di;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ExecutorsModule.class, ViewModelModule.class})
public class ApplicationModule {

    @Provides
    @Singleton
    static Resources provideResources(Context appContext){
        return appContext.getResources();
    }

    @Provides
    @Singleton
    @UI
    public Handler provideUiHandler(){
        return new Handler(Looper.getMainLooper());
    }

}
