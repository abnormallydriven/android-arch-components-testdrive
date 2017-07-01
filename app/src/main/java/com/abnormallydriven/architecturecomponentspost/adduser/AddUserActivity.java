package com.abnormallydriven.architecturecomponentspost.adduser;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.abnormallydriven.architecturecomponentspost.R;
import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.databinding.ActivityAddUserBinding;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class AddUserActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    @Inject
    LifecycleRegistry lifecycleRegistry;

    @Inject
    ApplicationViewModelFactory applicationViewModelFactory;

    @Nullable
    AddUserViewModel addUserViewModel;

    @Nullable
    private ActivityAddUserBinding binding;
    private Observable.OnPropertyChangedCallback snackbarMessageObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setupViewModel();
        setupView();
    }

    private void setupViewModel() {
        addUserViewModel = ViewModelProviders.of(this, applicationViewModelFactory).get(AddUserViewModel.class);

        if(addUserViewModel != null){
            snackbarMessageObserver = new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable observable, int i) {
                    if(addUserViewModel.getShouldShowSavedSuccessMessage().get()){
                        showSaveDataSuccess();
                    }
                }
            };
            addUserViewModel.getShouldShowSavedSuccessMessage().addOnPropertyChangedCallback(snackbarMessageObserver);

            if(addUserViewModel.getShouldShowSavedSuccessMessage().get()){
                showSaveDataSuccess();
            }
        }
    }

    private void showSaveDataSuccess() {
        if (addUserViewModel != null) {
            addUserViewModel.onShowSaveSuccessMessage();
        }
        Snackbar.make(binding.getRoot(), "User saved successfully!", Snackbar.LENGTH_LONG).show();
    }

    private void setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user);

        binding.saveUserFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addUserViewModel != null){
                    addUserViewModel.onAddUserClick();
                }
            }
        });

        binding.setViewModel(addUserViewModel);
    }

    @Override
    protected void onDestroy() {
        if (addUserViewModel != null) {
            addUserViewModel.getShouldShowSavedSuccessMessage().removeOnPropertyChangedCallback(snackbarMessageObserver);
        }
        super.onDestroy();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
