package com.abnormallydriven.architecturecomponentspost.adduser;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = AddUserActivityModule.class)
public interface AddUserActivitySubcomponent extends AndroidInjector<AddUserActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AddUserActivity>{}

}
