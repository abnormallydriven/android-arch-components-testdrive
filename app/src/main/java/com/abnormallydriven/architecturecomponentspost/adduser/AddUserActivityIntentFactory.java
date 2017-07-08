package com.abnormallydriven.architecturecomponentspost.adduser;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddUserActivityIntentFactory {

    private final Context appContext;

    @Inject
    AddUserActivityIntentFactory(Context appContext){
        this.appContext = appContext;
    }

    public Intent createAddUserActivityIntent(){
        return new Intent(appContext, AddUserActivity.class);
    }

}
