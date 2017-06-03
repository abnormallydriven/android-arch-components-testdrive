package com.abnormallydriven.architecturecomponentspost.data;


import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private static final String DATABASE_NAME_QUALIFIER = "databasename";

    @Provides
    @Named(DATABASE_NAME_QUALIFIER)
    static String provideDatabasename(){
        return "measurement.db";
    }

    @Singleton
    @Provides
    static MeasurementDatabase measurementDatabase(Application app, @Named(DATABASE_NAME_QUALIFIER) String databaseName){
        return Room.databaseBuilder(app, MeasurementDatabase.class, databaseName).build();
    }

}
