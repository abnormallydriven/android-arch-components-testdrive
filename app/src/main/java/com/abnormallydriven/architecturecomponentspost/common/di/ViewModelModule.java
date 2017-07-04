package com.abnormallydriven.architecturecomponentspost.common.di;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.abnormallydriven.architecturecomponentspost.addmeasurements.AddMeasurementsViewModel;
import com.abnormallydriven.architecturecomponentspost.adduser.AddUserViewModel;
import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.userlist.UserListViewModel;
import com.abnormallydriven.architecturecomponentspost.usermeasurements.UserMeasurementViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelMapKey(AddMeasurementsViewModel.class)
    abstract ViewModel bindAddMeasurementViewModel(AddMeasurementsViewModel addMeasurementsViewModel);

    @Binds
    @IntoMap
    @ViewModelMapKey(UserListViewModel.class)
    abstract ViewModel bindUserListViewModel(UserListViewModel userListViewModel);

    @Binds
    @IntoMap
    @ViewModelMapKey(AddUserViewModel.class)
    abstract ViewModel bindAddUserViewModel(AddUserViewModel addUserViewModel);

    @Binds
    @IntoMap
    @ViewModelMapKey(UserMeasurementViewModel.class)
    abstract ViewModel bindUserMeasurementViewModel(UserMeasurementViewModel userMeasurementViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ApplicationViewModelFactory viewModelFactory);

}
