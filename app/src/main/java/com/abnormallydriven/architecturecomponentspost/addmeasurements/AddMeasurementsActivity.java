package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.databinding.ActivityAddMeasurementsBinding;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class AddMeasurementsActivity extends AppCompatActivity implements LifecycleOwner {

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    ApplicationViewModelFactory applicationViewModelFactory;

    @Nullable
    AddMeasurementsViewModel viewModel;

    private ActivityAddMeasurementsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setupViewModel();
        setupView();
    }

    private void setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_measurements);

        viewModel.getMeasurement().observe(this, new Observer<Measurement>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable Measurement measurement) {

                if(measurement == null){

                }

                binding.lowerLeftBicepContainer.measurementValue.setText(String.valueOf(measurement.getLowerLeftBicep()));
                binding.lowerRightBicepContainer.measurementValue.setText(String.valueOf(measurement.getLowerRightBicep()));


            }
        });

    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this, applicationViewModelFactory).get(AddMeasurementsViewModel.class);
    }

    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
