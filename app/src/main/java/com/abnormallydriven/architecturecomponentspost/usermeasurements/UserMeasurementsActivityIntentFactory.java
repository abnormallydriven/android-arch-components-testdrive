package com.abnormallydriven.architecturecomponentspost.usermeasurements;


import android.content.Context;
import android.content.Intent;

import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserMeasurementsActivityIntentFactory {

    static final String SELECTED_USER_INTENT_KEY = "user";

    private final Context context;

    @Inject
    UserMeasurementsActivityIntentFactory(Context context){
        this.context = context;
    }

    public Intent createUserMeasurementsActivityIntent(User selectedUser){
        Intent intent = new Intent(context, UserMeasurementsActivity.class);
        intent.putExtra(SELECTED_USER_INTENT_KEY, selectedUser);
        return intent;
    }

}
