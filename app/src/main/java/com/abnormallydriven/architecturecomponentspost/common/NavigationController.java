package com.abnormallydriven.architecturecomponentspost.common;

import android.content.Context;
import android.content.Intent;

import com.abnormallydriven.architecturecomponentspost.addmeasurements.AddMeasurementsActivity;
import com.abnormallydriven.architecturecomponentspost.adduser.AddUserActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NavigationController {

    private final Context appContext;

    @Inject
    public NavigationController(Context appContext){
        this.appContext = appContext;
    }


    public void navigateToUserAdd(){
        //TODO activity intent factories
        appContext.startActivity(new Intent(appContext, AddUserActivity.class));
    }

    public void navigateToAddMeasurement(){
        appContext.startActivity(new Intent(appContext, AddMeasurementsActivity.class));
    }

}
