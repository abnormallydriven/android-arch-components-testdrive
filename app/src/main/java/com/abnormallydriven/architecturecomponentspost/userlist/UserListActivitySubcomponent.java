package com.abnormallydriven.architecturecomponentspost.userlist;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface UserListActivitySubcomponent extends AndroidInjector<UserListActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<UserListActivity>{}
}
