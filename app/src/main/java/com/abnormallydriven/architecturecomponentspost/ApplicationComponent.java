package com.abnormallydriven.architecturecomponentspost;


import android.app.Application;

import com.abnormallydriven.architecturecomponentspost.data.DatabaseModule;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {DatabaseModule.class, ApplicationModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance Builder application(Application application);
        ApplicationComponent build();
    }

}
