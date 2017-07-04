package com.abnormallydriven.architecturecomponentspost.usermeasurements;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.databinding.ActivityUserMeasurementsBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class UserMeasurementsActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    ApplicationViewModelFactory applicationViewModelFactory;

    @Inject
    UserMeasurementsAdapter userMeasurementsAdapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ActivityUserMeasurementsBinding binding;

    private UserMeasurementViewModel userMeasurementViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_measurements);
        setupViewModel(savedInstanceState);
        setupView();
    }

    private void setupView() {
        binding.measurementsRecyclerView.setLayoutManager(linearLayoutManager);
        binding.measurementsRecyclerView.setAdapter(userMeasurementsAdapter);
    }

    private void setupViewModel(Bundle savedInstanceState) {
        userMeasurementViewModel = ViewModelProviders.of(this, applicationViewModelFactory).get(UserMeasurementViewModel.class);

        if(savedInstanceState == null){
            User user = getIntent().getParcelableExtra("user");
            userMeasurementViewModel.onRefreshMeasurements(user.getId());
        }

        userMeasurementViewModel.getUserMeasurements().observe(this, new Observer<Measurement[]>() {
            @Override
            public void onChanged(@Nullable Measurement[] measurements) {
                if(measurements != null){
                    userMeasurementsAdapter.update(measurements);
                } else {
                    userMeasurementsAdapter.update(new Measurement[0]);
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("layoutManagerState", linearLayoutManager.onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        linearLayoutManager.onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        binding.measurementsRecyclerView.setAdapter(null);
        super.onDestroy();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
