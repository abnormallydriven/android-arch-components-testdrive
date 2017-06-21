package com.abnormallydriven.architecturecomponentspost.userlist;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.abnormallydriven.architecturecomponentspost.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.data.entities.User;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserListViewModelTest {

    UserListViewModel objectUnderTest;
    private UserDao mockDao;

    @Before
    public void setup(){
        ExecutorService fakeExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                return Thread.currentThread();
            }
        });

        mockDao = mock(UserDao.class);

        objectUnderTest = new UserListViewModel(fakeExecutor, fakeExecutor, mockDao);

    }

    @Test
    public void onRefreshUserListGetsLatestDaoUsers(){
        MutableLiveData<User[]> fakeMutableData = new MutableLiveData<>();

        when(mockDao.getAllUsers()).thenReturn(fakeMutableData);


    }


}