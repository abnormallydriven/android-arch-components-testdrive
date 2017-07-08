package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import android.content.Context;
import android.content.Intent;

import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddMeasurementsActivityIntentFactory {

    static final String USER_EXTRA_KEY = "user";
    private final Context appContext;

    @Inject
    AddMeasurementsActivityIntentFactory(Context appContext){
        this.appContext = appContext;
    }

    public Intent createNewIntent(User measurementUser){
        Intent intent = new Intent(appContext, AddMeasurementsActivity.class);
        intent.putExtra(USER_EXTRA_KEY, measurementUser);
        return intent;
    }

}
