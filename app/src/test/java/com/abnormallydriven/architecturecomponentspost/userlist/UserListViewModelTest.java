package com.abnormallydriven.architecturecomponentspost.userlist;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.abnormallydriven.architecturecomponentspost.common.NavigationController;
import com.abnormallydriven.architecturecomponentspost.common.data.UserDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.utils.SynchronousExecutor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.Executor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class UserListViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    UserListViewModel objectUnderTest;
    private UserDao mockDao;
    private NavigationController mockNavController;

    @Before
    public void setup(){
        Executor synchronousExecutor = new SynchronousExecutor();

        mockDao = mock(UserDao.class);
        mockNavController = mock(NavigationController.class);

        objectUnderTest = new UserListViewModel(synchronousExecutor, mockNavController, mockDao);
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

        objectUnderTest.getUsers().observeForever(mock(Observer.class));

        objectUnderTest.onRefreshUserList();

        User[] returnedUsers = objectUnderTest.getUsers().getValue();

        verify(mockDao, times(1)).getAllUsers();

        assertArrayEquals(fakeUsers, returnedUsers);
    }

    @Test
    public void onUserAddClick(){

        objectUnderTest.onUserAddClick();

        verify(mockNavController, times(1)).navigateToUserAdd();

    }

}