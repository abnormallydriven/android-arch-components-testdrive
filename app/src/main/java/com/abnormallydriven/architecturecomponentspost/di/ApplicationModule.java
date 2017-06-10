package com.abnormallydriven.architecturecomponentspost.di;


import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    static Resources provideResources(Application application){
        return application.getResources();
    }


}
