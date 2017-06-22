package com.abnormallydriven.architecturecomponentspost.common.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;

@Database(version = 1, entities = {User.class, Measurement.class})
@TypeConverters({RoomTypeConverters.class})
public abstract class MeasurementDatabase extends RoomDatabase {

    abstract public UserDao userDao();

    abstract public MeasurementDao measurementDao();

}
