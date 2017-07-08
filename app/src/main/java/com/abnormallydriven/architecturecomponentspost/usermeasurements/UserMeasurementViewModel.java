package com.abnormallydriven.architecturecomponentspost.usermeasurements;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.abnormallydriven.architecturecomponentspost.common.NavigationController;
import com.abnormallydriven.architecturecomponentspost.common.data.MeasurementDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.common.di.Disk;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserMeasurementViewModel extends ViewModel {

    @NonNull
    private final Executor diskExecutor;

    @NonNull
    private final MeasurementDao measurementDao;

    @NonNull
    private final NavigationController navigationController;

    @NonNull
    private final MutableLiveData<Measurement[]> measurementsLiveData;

    @NonNull
    public final ObservableBoolean shouldShowProgressSpinner;

    @Inject
    UserMeasurementViewModel(@NonNull @Disk Executor diskExecutor,
                             @NonNull MeasurementDao measurementDao,
                             @NonNull NavigationController navigationController){
        this.diskExecutor = diskExecutor;
        this.measurementDao = measurementDao;
        this.navigationController = navigationController;

        measurementsLiveData = new MutableLiveData<>();
        measurementsLiveData.setValue(new Measurement[0]);

        shouldShowProgressSpinner = new ObservableBoolean(false);
    }

    @NonNull
    LiveData<Measurement[]> getUserMeasurements(){
        return measurementsLiveData;
    }

    void onRefreshMeasurements(final long userId){
        shouldShowProgressSpinner.set(true);

        diskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final LiveData<Measurement[]> liveData = measurementDao.getUserMeasurements(userId);
                liveData.observeForever(new Observer<Measurement[]>() {
                    @Override
                    public void onChanged(@Nullable Measurement[] measurements) {
                        measurementsLiveData.setValue(measurements);
                        liveData.removeObserver(this);
                        shouldShowProgressSpinner.set(false);
                    }
                });
            }
        });
    }


    @Override
    protected void onCleared() {
        measurementsLiveData.setValue(new Measurement[0]);
        shouldShowProgressSpinner.set(false);
    }

    void onAddMeasurementClick(User selectedUser) {
        navigationController.navigateToAddMeasurement(selectedUser);
    }
}
