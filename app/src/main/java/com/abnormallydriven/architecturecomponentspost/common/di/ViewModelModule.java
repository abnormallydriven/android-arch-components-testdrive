package com.abnormallydriven.architecturecomponentspost.di;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.abnormallydriven.architecturecomponentspost.common.ApplicationViewModelFactory;
import com.abnormallydriven.architecturecomponentspost.userlist.UserListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelMapKey(UserListViewModel.class)
    abstract ViewModel bindUserListViewModel(UserListViewModel userListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ApplicationViewModelFactory viewModelFactory);

}
