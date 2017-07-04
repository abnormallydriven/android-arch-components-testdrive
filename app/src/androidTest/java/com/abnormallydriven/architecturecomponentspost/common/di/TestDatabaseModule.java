package com.abnormallydriven.architecturecomponentspost.common.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.abnormallydriven.architecturecomponentspost.common.data.MeasurementDao;
import com.abnormallydriven.architecturecomponentspost.common.data.MeasurementDatabase;
import com.abnormallydriven.architecturecomponentspost.common.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.common.data.migrations.Migration1To2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestDatabaseModule {

    @Singleton
    @Provides
    static MeasurementDatabase provideMeasurementDatabase(Context appContext){
        return Room.inMemoryDatabaseBuilder(appContext, MeasurementDatabase.class)
                .addMigrations(new Migration1To2(1,2))
                .build();
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
