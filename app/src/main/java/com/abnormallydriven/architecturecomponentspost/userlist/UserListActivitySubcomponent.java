package com.abnormallydriven.architecturecomponentspost.userlist;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = UserListActivityModule.class)
public interface UserListActivitySubcomponent extends AndroidInjector<UserListActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserListActivity>{}
}
