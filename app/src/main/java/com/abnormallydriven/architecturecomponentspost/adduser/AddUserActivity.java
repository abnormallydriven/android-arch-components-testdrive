package com.abnormallydriven.architecturecomponentspost.adduser;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.databinding.ActivityAddUserBinding;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class AddUserActivity extends AppCompatActivity implements LifecycleOwner {

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    ApplicationViewModelFactory applicationViewModelFactory;

    @Nullable
    AddUserViewModel addUserViewModel;

    @Nullable
    private ActivityAddUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setupViewModel();
        setupView();
    }

    private void setupViewModel() {
        addUserViewModel = ViewModelProviders.of(this, applicationViewModelFactory).get(AddUserViewModel.class);
    }

    private void setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user);

        binding.saveUserFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.setViewModel(addUserViewModel);
    }

    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
