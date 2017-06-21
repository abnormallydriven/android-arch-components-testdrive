package com.abnormallydriven.architecturecomponentspost.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.abnormallydriven.architecturecomponentspost.LiveDataTestUtil;
import com.abnormallydriven.architecturecomponentspost.di.DaggerTestApplicationComponent;
import com.abnormallydriven.architecturecomponentspost.di.TestApplicationComponent;
import com.abnormallydriven.architecturecomponentspost.data.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserDaoTests {

    private MeasurementDatabase measurementDatabase;
    private UserDao userDao;

    @Before
    public void setup(){
        Context targetContext = InstrumentationRegistry.getTargetContext();
        TestApplicationComponent daggerTestComponent = DaggerTestApplicationComponent.builder()
            .applicationContext(targetContext)
            .build();

        measurementDatabase = daggerTestComponent.measurementDatabase();
        userDao = measurementDatabase.userDao();
    }

    @After
    public void after(){
        measurementDatabase.close();
    }

    @Test
    public void testUserInsert(){
        User testUser = getTestUser("Brett", "Tucker", 29, "Male");

        long insertedUserId = userDao.inserUser(testUser);
        User userById = LiveDataTestUtil.getValue(userDao.getUserById(insertedUserId));

        assertEquals(testUser.getAge(), userById.getAge());
    }

    @Test
    public void testUserUpdate(){
        User testUser = getTestUser("Brett", "Tucker", 29, "Male");
        long insertedUserId = userDao.inserUser(testUser);
        testUser.setId(insertedUserId);

        testUser.setAge(30);
        userDao.updateUser(testUser);

        User userByUserId = LiveDataTestUtil.getValue(userDao.getUserById(insertedUserId));

        assertEquals(30, userByUserId.getAge());
    }

    @Test
    public void testUserDelete(){
        User testUser = getTestUser("Brett", "Tucker", 29, "Male");
        long insertedUserId = userDao.inserUser(testUser);
        testUser.setId(insertedUserId);

        userDao.deleteUser(testUser);

        User userById = LiveDataTestUtil.getValue(userDao.getUserById(insertedUserId));

        assertNull(userById);

    }

    @Test
    public void testGetAllUsersQuery(){
        User brett = getTestUser("Brett", "Tucker", 29, "Male");
        User john = getTestUser("John", "Smith", 30, "Male)");

        userDao.inserUser(brett);
        userDao.inserUser(john);

        User[] allUsers = LiveDataTestUtil.getValue(userDao.getAllUsers());

        assertEquals(2, allUsers.length);

    }

    @NonNull
    private User getTestUser(String firstname, String lastName, int age, String gender) {
        User testUser = new User(firstname, lastName, age, gender);
        return testUser;
    }

}