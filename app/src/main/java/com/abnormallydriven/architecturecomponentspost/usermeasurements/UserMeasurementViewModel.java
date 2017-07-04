package com.abnormallydriven.architecturecomponentspost.usermeasurements;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.abnormallydriven.architecturecomponentspost.common.data.MeasurementDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.common.di.Disk;
import com.abnormallydriven.architecturecomponentspost.common.di.UI;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserMeasurementViewModel extends ViewModel {

    @NonNull
    private final Executor uiExecutor;

    @NonNull
    private final Executor diskExecutor;

    @NonNull
    private final MeasurementDao measurementDao;

    @NonNull
    private final MutableLiveData<Measurement[]> measurementsLiveData;

    @Inject
    UserMeasurementViewModel(@NonNull @UI Executor uiExecutor, @NonNull @Disk Executor diskExecutor, @NonNull MeasurementDao measurementDao){
        this.uiExecutor = uiExecutor;
        this.diskExecutor = diskExecutor;
        this.measurementDao = measurementDao;

        measurementsLiveData = new MutableLiveData<>();
        measurementsLiveData.setValue(new Measurement[0]);
    }

    LiveData<Measurement[]> getUserMeasurements(){
        return measurementsLiveData;
    }

    void onRefreshMeasurements(final long userId){
        //TODO spinners
        diskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final LiveData<Measurement[]> liveData = measurementDao.getUserMeasurements(userId);
                liveData.observeForever(new Observer<Measurement[]>() {
                    @Override
                    public void onChanged(@Nullable Measurement[] measurements) {
                        measurementsLiveData.setValue(measurements);
                        liveData.removeObserver(this);
                    }
                });
            }
        });
    }


    @Override
    protected void onCleared() {
        //todo
    }
}
