package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableFloat;
import android.support.annotation.NonNull;

import com.abnormallydriven.architecturecomponentspost.common.data.MeasurementDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.common.di.Disk;
import com.abnormallydriven.architecturecomponentspost.common.di.UI;

import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

//TODO tests

@Singleton
public class AddMeasurementsViewModel extends ViewModel {

    @NonNull
    private final Executor uiExecutor;

    @NonNull
    private final Executor diskExecutor;

    @NonNull
    private final MeasurementDao measurementDao;

    @NonNull
    private final ObservableFloat lowerLeftBicep;

    @NonNull
    private final ObservableFloat lowerRightBicep;

    @NonNull
    private final ObservableFloat leftBicep;

    @NonNull
    private final ObservableFloat rightBicep;

    @NonNull
    private final ObservableFloat leftCalf;

    @NonNull
    private final ObservableFloat rightCalf;

    @NonNull
    private final ObservableFloat chest;

    @NonNull
    private final ObservableFloat leftForearm;

    @NonNull
    private final ObservableFloat rightForearm;

    @NonNull
    private final ObservableFloat highHip;

    @NonNull
    private final ObservableFloat hip;

    @NonNull
    private final ObservableFloat lowerLeftThigh;

    @NonNull
    private final ObservableFloat lowerRightThigh;

    @NonNull
    private final ObservableFloat midLeftThigh;

    @NonNull
    private final ObservableFloat midRightThigh;

    @NonNull
    private final ObservableFloat upperLeftThigh;

    @NonNull
    private final ObservableFloat upperRightThigh;

    @NonNull
    private final ObservableFloat abdominalWaist;

    @NonNull
    private final ObservableFloat lowerWaist;

    @NonNull
    private final ObservableFloat narrowestWaist;

    @NonNull
    public final ObservableBoolean shouldShowProgressSpinner;

    @NonNull
    public final ObservableBoolean shouldShowSaveSuccessMessage;

    @Inject
    public AddMeasurementsViewModel(@UI @NonNull Executor uiExecutor,
                                    @Disk @NonNull Executor diskExecutor,
                                    MeasurementDao measurementDao) {
        this.uiExecutor = uiExecutor;
        this.diskExecutor = diskExecutor;
        this.measurementDao = measurementDao;
        this.shouldShowProgressSpinner = new ObservableBoolean(false);
        this.shouldShowSaveSuccessMessage = new ObservableBoolean(false);

        lowerLeftBicep = new ObservableFloat(0.0f);
        lowerRightBicep = new ObservableFloat(0.0f);
        leftBicep = new ObservableFloat(0.0f);
        rightBicep = new ObservableFloat(0.0f);
        leftCalf = new ObservableFloat(0.0f);
        rightCalf = new ObservableFloat(0.0f);
        chest = new ObservableFloat(0.0f);
        leftForearm = new ObservableFloat(0.0f);
        rightForearm = new ObservableFloat(0.0f);
        highHip = new ObservableFloat(0.0f);
        hip = new ObservableFloat(0.0f);
        lowerLeftThigh = new ObservableFloat(0.0f);
        lowerRightThigh = new ObservableFloat(0.0f);
        midLeftThigh = new ObservableFloat(0.0f);
        midRightThigh = new ObservableFloat(0.0f);
        upperLeftThigh = new ObservableFloat(0.0f);
        upperRightThigh = new ObservableFloat(0.0f);
        abdominalWaist = new ObservableFloat(0.0f);
        lowerWaist = new ObservableFloat(0.0f);
        narrowestWaist = new ObservableFloat(0.0f);
    }


    @NonNull
    public ObservableFloat getLowerLeftBicep() {
        return lowerLeftBicep;
    }

    @NonNull
    public ObservableFloat getLowerRightBicep() {
        return lowerRightBicep;
    }

    @NonNull
    public ObservableFloat getLeftBicep() {
        return leftBicep;
    }

    @NonNull
    public ObservableFloat getRightBicep() {
        return rightBicep;
    }

    @NonNull
    public ObservableFloat getLeftCalf() {
        return leftCalf;
    }

    @NonNull
    public ObservableFloat getRightCalf() {
        return rightCalf;
    }

    @NonNull
    public ObservableFloat getChest() {
        return chest;
    }

    @NonNull
    public ObservableFloat getLeftForearm() {
        return leftForearm;
    }

    @NonNull
    public ObservableFloat getRightForearm() {
        return rightForearm;
    }

    @NonNull
    public ObservableFloat getHighHip() {
        return highHip;
    }

    @NonNull
    public ObservableFloat getHip() {
        return hip;
    }

    @NonNull
    public ObservableFloat getLowerLeftThigh() {
        return lowerLeftThigh;
    }

    @NonNull
    public ObservableFloat getLowerRightThigh() {
        return lowerRightThigh;
    }

    @NonNull
    public ObservableFloat getMidLeftThigh() {
        return midLeftThigh;
    }

    @NonNull
    public ObservableFloat getMidRightThigh() {
        return midRightThigh;
    }

    @NonNull
    public ObservableFloat getUpperLeftThigh() {
        return upperLeftThigh;
    }

    @NonNull
    public ObservableFloat getUpperRightThigh() {
        return upperRightThigh;
    }

    @NonNull
    public ObservableFloat getAbdominalWaist() {
        return abdominalWaist;
    }

    @NonNull
    public ObservableFloat getLowerWaist() {
        return lowerWaist;
    }

    @NonNull
    public ObservableFloat getNarrowestWaist() {
        return narrowestWaist;
    }

    void onSaveMeasurementForUserClick(final User user) {
        shouldShowProgressSpinner.set(true);

        diskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Measurement newMeasurement = new Measurement();

                newMeasurement.setLowerLeftBicep(lowerLeftBicep.get());
                newMeasurement.setLowerRightBicep(lowerRightBicep.get());
                newMeasurement.setLeftBicep(leftBicep.get());
                newMeasurement.setRightBicep(rightBicep.get());
                newMeasurement.setLeftCalf(leftCalf.get());
                newMeasurement.setRightCalf(rightCalf.get());
                newMeasurement.setChest(chest.get());
                newMeasurement.setLeftForearm(leftForearm.get());
                newMeasurement.setRightForearm(rightForearm.get());
                newMeasurement.setHighHip(highHip.get());
                newMeasurement.setHighHip(hip.get());
                newMeasurement.setLowerLeftThigh(lowerLeftThigh.get());
                newMeasurement.setLowerRightThigh(lowerRightThigh.get());
                newMeasurement.setMidLeftThigh(midLeftThigh.get());
                newMeasurement.setMidRightThigh(midRightThigh.get());
                newMeasurement.setUpperLeftThigh(upperLeftThigh.get());
                newMeasurement.setUpperRightThigh(upperRightThigh.get());
                newMeasurement.setAbdominalWaist(abdominalWaist.get());
                newMeasurement.setLowerWaist(lowerWaist.get());
                newMeasurement.setNarrowestWaist(narrowestWaist.get());
                newMeasurement.setMeasurementDate(new Date());
                newMeasurement.setUserId(user.getId());

                measurementDao.insertMeasurements(newMeasurement);


                uiExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        shouldShowProgressSpinner.set(false);
                        shouldShowSaveSuccessMessage.set(true);

                        lowerLeftBicep.set(0.0f);
                        lowerRightBicep.set(0.0f);
                        rightBicep.set(0.0f);
                        leftBicep.set(0.0f);
                        leftCalf.set(0.0f);
                        rightCalf.set(0.0f);
                        chest.set(0.0f);
                        leftForearm.set(0.0f);
                        rightForearm.set(0.0f);
                        highHip.set(0.0f);
                        hip.set(0.0f);
                        lowerRightThigh.set(0.0f);
                        lowerLeftThigh.set(0.0f);
                        midLeftThigh.set(0.0f);
                        midRightThigh.set(0.0f);
                        upperLeftThigh.set(0.0f);
                        upperRightThigh.set(0.0f);
                        abdominalWaist.set(0.0f);
                        lowerWaist.set(0.0f);
                        narrowestWaist.set(0.0f);
                    }
                });


            }
        });
    }
}
