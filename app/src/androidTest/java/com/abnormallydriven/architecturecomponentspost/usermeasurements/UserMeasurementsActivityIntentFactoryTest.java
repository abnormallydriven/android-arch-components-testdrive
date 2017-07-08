package com.abnormallydriven.architecturecomponentspost.usermeasurements;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserMeasurementsActivityIntentFactoryTest {

    @Test
    public void createUserMeasurementsActivityIntent() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        UserMeasurementsActivityIntentFactory objectUnderTest = new UserMeasurementsActivityIntentFactory(context);

        final User fakeUser = new User("Fake", "User", 29, "M");

        Intent intent = objectUnderTest.createUserMeasurementsActivityIntent(fakeUser);

        assertTrue(intent.hasExtra(UserMeasurementsActivityIntentFactory.SELECTED_USER_INTENT_KEY));
        assertEquals(fakeUser, intent.getParcelableExtra(UserMeasurementsActivityIntentFactory.SELECTED_USER_INTENT_KEY));
    }

}