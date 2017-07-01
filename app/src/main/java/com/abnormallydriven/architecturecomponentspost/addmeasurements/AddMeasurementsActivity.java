package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.databinding.ActivityAddMeasurementsBinding;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class AddMeasurementsActivity extends AppCompatActivity implements LifecycleRegistryOwner {

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
        binding.setViewModel(viewModel);

        viewModel.shouldShowSaveSuccessMessage.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if(viewModel.shouldShowSaveSuccessMessage.get()){
                    showMeasurementSavedMessage();
                }
            }
        });

        if(viewModel.shouldShowSaveSuccessMessage.get()){
            showMeasurementSavedMessage();
        }

    }

    private void showMeasurementSavedMessage() {
        viewModel.shouldShowSaveSuccessMessage.set(false);
        Snackbar.make(binding.getRoot(), "Measurement Saved Successfully!", Snackbar.LENGTH_INDEFINITE).show();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this, applicationViewModelFactory).get(AddMeasurementsViewModel.class);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
