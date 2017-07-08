package com.abnormallydriven.architecturecomponentspost.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.abnormallydriven.architecturecomponentspost.addmeasurements.AddMeasurementsActivityIntentFactory;
import com.abnormallydriven.architecturecomponentspost.adduser.AddUserActivityIntentFactory;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.usermeasurements.UserMeasurementsActivityIntentFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NavigationController {

    @NonNull
    private final Context appContext;

    @NonNull
    private final AddMeasurementsActivityIntentFactory addMeasurementsActivityIntentFactory;

    @NonNull
    private final AddUserActivityIntentFactory addUserActivityIntentFactory;

    @NonNull
    private final UserMeasurementsActivityIntentFactory userMeasurementsActivityIntentFactory;

    @Inject
    public NavigationController(@NonNull Context appContext,
                                @NonNull AddMeasurementsActivityIntentFactory addMeasurementsActivityIntentFactory,
                                @NonNull AddUserActivityIntentFactory addUserActivityIntentFactory,
                                @NonNull UserMeasurementsActivityIntentFactory userMeasurementsActivityIntentFactory){
        this.appContext = appContext;
        this.addMeasurementsActivityIntentFactory = addMeasurementsActivityIntentFactory;
        this.addUserActivityIntentFactory = addUserActivityIntentFactory;
        this.userMeasurementsActivityIntentFactory = userMeasurementsActivityIntentFactory;
    }


    public void navigateToUserAdd(){
        Intent addUserActivityIntent = addUserActivityIntentFactory.createAddUserActivityIntent();
        appContext.startActivity(addUserActivityIntent);
    }

    public void navigateToUserMeasurements(User selectedUser){
        Intent intent = userMeasurementsActivityIntentFactory.createUserMeasurementsActivityIntent(selectedUser);
        appContext.startActivity(intent);
    }

    public void navigateToAddMeasurement(User selectedUser){
        Intent intent = addMeasurementsActivityIntentFactory.createNewIntent(selectedUser);
        appContext.startActivity(intent);
    }

}
