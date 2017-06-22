package com.abnormallydriven.architecturecomponentspost.data;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abnormallydriven.architecturecomponentspost.data.entities.Measurement;

@Dao
public interface MeasurementDao {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    long insertMeasurements(Measurement measurement);

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    void updateMeasurement(Measurement measurement);

    @Delete
    void deleteMeasurement(Measurement measurement);

    @Query("SELECT * from measurements where userId = :userId")
    Measurement[] getUserMeasurements(long userId);
}
