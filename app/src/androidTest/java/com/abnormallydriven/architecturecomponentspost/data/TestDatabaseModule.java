package com.abnormallydriven.architecturecomponentspost.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestDatabaseModule {

    @Singleton
    @Provides
    static MeasurementDatabase provideMeasurementDatabase(Context appContext){
        return Room.inMemoryDatabaseBuilder(appContext, MeasurementDatabase.class).build();
    }

    @Singleton
    @Provides
    static UserDao provideUserDao(MeasurementDatabase measurementDb){
        return measurementDb.userDao();
    }

    @Singleton
    @Provides
    static MeasurementDao provideMeasurementDao(MeasurementDatabase measurementDb){
        return measurementDb.measurementDao();
    }

}
