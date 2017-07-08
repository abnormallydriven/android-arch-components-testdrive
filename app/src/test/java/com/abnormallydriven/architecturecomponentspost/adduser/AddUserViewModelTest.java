package com.abnormallydriven.architecturecomponentspost.adduser;

import com.abnormallydriven.architecturecomponentspost.common.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.utils.SynchronousExecutor;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AddUserViewModelTest {

    private AddUserViewModel objectUnderTest;

    @Before
    public void setup(){
        Executor synchronousExecutor = new SynchronousExecutor();
        UserDao mockUserDao = mock(UserDao.class);
        objectUnderTest = new AddUserViewModel(synchronousExecutor, synchronousExecutor, mockUserDao);
    }

    @Test
    public void isValidFormShouldOnlyBeTrueWhenAllFieldsContainValues(){
        assertFalse(objectUnderTest.isValidForm().get());

        objectUnderTest.getFirstName().set("brett");
        assertFalse(objectUnderTest.isValidForm().get());


        objectUnderTest.getLastName().set("tucker");
        assertFalse(objectUnderTest.isValidForm().get());

        objectUnderTest.getAge().set("29");
        assertFalse(objectUnderTest.isValidForm().get());

        objectUnderTest.getGender().set("M");
        assertTrue(objectUnderTest.isValidForm().get());
    }

    @Test
    public void onClearedShouldResetViewModel(){
        objectUnderTest.getFirstName().set("john");
        objectUnderTest.getLastName().set("test");
        objectUnderTest.getAge().set("99");
        objectUnderTest.getGender().set("M");
        objectUnderTest.getIsSavingData().set(true);
        objectUnderTest.getShouldShowSavedSuccessMessage().set(true);

        objectUnderTest.onCleared();

        assertTrue(objectUnderTest.getFirstName().get().isEmpty());
        assertTrue(objectUnderTest.getLastName().get().isEmpty());
        assertTrue(objectUnderTest.getAge().get().isEmpty());
        assertTrue(objectUnderTest.getGender().get().isEmpty());
        assertFalse(objectUnderTest.getIsSavingData().get());
        assertFalse(objectUnderTest.getShouldShowSavedSuccessMessage().get());
    }


}