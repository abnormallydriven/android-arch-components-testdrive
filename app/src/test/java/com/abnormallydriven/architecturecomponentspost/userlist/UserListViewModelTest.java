package com.abnormallydriven.architecturecomponentspost.userlist;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.abnormallydriven.architecturecomponentspost.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.utils.SynchronousExecutor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.Executor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UserListViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    UserListViewModel objectUnderTest;
    private UserDao mockDao;

    @Before
    public void setup(){
        Executor synchronousExecutor = new SynchronousExecutor();

        mockDao = mock(UserDao.class);

        objectUnderTest = new UserListViewModel(synchronousExecutor, synchronousExecutor, mockDao);
    }

    @Test
    public void onRefreshUserListGetsLatestDaoUsers(){
        final User fakeUserA = new User("John", "Test", 20, "M");
        final User fakeUserB = new User("Jane", "Test", 20, "F");

        final User[] fakeUsers = new User[]{fakeUserA, fakeUserB};
        MutableLiveData<User[]> fakeMutableData = new MutableLiveData<>();

        fakeMutableData.setValue(fakeUsers);

        User[] emptyUsers = objectUnderTest.getUsers().getValue();

        assertNotNull(emptyUsers);
        assertEquals(0, emptyUsers.length);

        when(mockDao.getAllUsers()).thenReturn(fakeMutableData);

        objectUnderTest.onRefreshUserList();

        User[] returnedUsers = objectUnderTest.getUsers().getValue();

        verify(mockDao, times(1)).getAllUsers();

        assertArrayEquals(fakeUsers, returnedUsers);
    }

}