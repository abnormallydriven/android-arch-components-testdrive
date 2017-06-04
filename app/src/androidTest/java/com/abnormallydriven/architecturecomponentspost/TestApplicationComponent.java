package com.abnormallydriven.architecturecomponentspost;


import android.content.Context;

import com.abnormallydriven.architecturecomponentspost.data.MeasurementDatabase;
import com.abnormallydriven.architecturecomponentspost.data.TestDatabaseModule;

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
