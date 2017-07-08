package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddMeasurementsActivityIntentFactoryTest {

    @Test
    public void intentShouldIncludeUserParcelable() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        AddMeasurementsActivityIntentFactory objectUnderTest = new AddMeasurementsActivityIntentFactory(context);

        User testUser = new User("John", "Test", 20, "M");
        Intent createdIntent = objectUnderTest.createNewIntent(testUser);

        assertTrue(createdIntent.hasExtra(AddMeasurementsActivityIntentFactory.USER_EXTRA_KEY));
        assertEquals(testUser, createdIntent.getParcelableExtra(AddMeasurementsActivityIntentFactory.USER_EXTRA_KEY));

    }

}