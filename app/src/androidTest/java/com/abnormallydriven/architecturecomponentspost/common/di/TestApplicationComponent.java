package com.abnormallydriven.architecturecomponentspost.di;


import android.content.Context;

import com.abnormallydriven.architecturecomponentspost.data.MeasurementDatabase;
import com.abnormallydriven.architecturecomponentspost.userlist.UserListActivityInjectorModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        TestDatabaseModule.class,
        ApplicationModule.class,
        UserListActivityInjectorModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

    MeasurementDatabase measurementDatabase();

    @Component.Builder
    interface Builder{
        @BindsInstance
        TestApplicationComponent.Builder applicationContext(Context applicationContext);
        TestApplicationComponent build();
    }

}
