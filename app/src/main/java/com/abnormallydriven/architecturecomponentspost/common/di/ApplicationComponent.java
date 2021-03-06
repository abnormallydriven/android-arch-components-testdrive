package com.abnormallydriven.architecturecomponentspost.common.di;


import android.content.Context;

import com.abnormallydriven.architecturecomponentspost.MeasurementTrackerApp;
import com.abnormallydriven.architecturecomponentspost.addmeasurements.AddMeasurementsActivityInjectorModule;
import com.abnormallydriven.architecturecomponentspost.adduser.AddUserActivityInjectorModule;
import com.abnormallydriven.architecturecomponentspost.userlist.UserListActivityInjectorModule;
import com.abnormallydriven.architecturecomponentspost.usermeasurements.UserMeasurementsActivityInjectorModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        DatabaseModule.class,
        ApplicationModule.class,
        UserListActivityInjectorModule.class,
        AddUserActivityInjectorModule.class,
        AddMeasurementsActivityInjectorModule.class,
        UserMeasurementsActivityInjectorModule.class})
public interface ApplicationComponent {

    void inject(MeasurementTrackerApp measurementTrackerApp);

    @Component.Builder
    interface Builder{
        @BindsInstance Builder applicationContext(Context applicationContext);
        ApplicationComponent build();
    }

}
