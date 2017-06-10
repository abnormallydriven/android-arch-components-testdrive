package com.abnormallydriven.architecturecomponentspost.di;


import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, ApplicationModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance Builder applicationContext(Context applicationContext);
        ApplicationComponent build();
    }

}
