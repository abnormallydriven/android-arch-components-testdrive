package com.abnormallydriven.architecturecomponentspost.di;


import android.content.Context;

import com.abnormallydriven.architecturecomponentspost.data.MeasurementDatabase;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, TestDatabaseModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

    MeasurementDatabase measurementDatabase();

    @Component.Builder
    interface Builder{
        @BindsInstance
        TestApplicationComponent.Builder applicationContext(Context applicationContext);
        TestApplicationComponent build();
    }

}
