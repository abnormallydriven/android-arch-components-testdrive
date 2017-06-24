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


}