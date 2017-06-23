package com.abnormallydriven.architecturecomponentspost;

import com.abnormallydriven.architecturecomponentspost.common.di.DaggerInjector;
import com.abnormallydriven.architecturecomponentspost.common.di.DaggerTestApplicationComponent;
import com.abnormallydriven.architecturecomponentspost.common.di.TestApplicationComponent;

public class TestMeasurementTrackerApp extends MeasurementTrackerApp {

    @Override
    protected void initializeDaggerInjector() {
        TestApplicationComponent daggerTestComponent = DaggerTestApplicationComponent.builder()
                .applicationContext(this)
                .build();

        DaggerInjector.initializeComponent(daggerTestComponent);
    }
}
