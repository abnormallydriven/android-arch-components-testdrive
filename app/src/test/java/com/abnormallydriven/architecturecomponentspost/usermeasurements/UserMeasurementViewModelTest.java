package com.abnormallydriven.architecturecomponentspost.usermeasurements;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.abnormallydriven.architecturecomponentspost.common.NavigationController;
import com.abnormallydriven.architecturecomponentspost.common.data.MeasurementDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.User;
import com.abnormallydriven.architecturecomponentspost.utils.SimplePropertyChangedObserver;
import com.abnormallydriven.architecturecomponentspost.utils.SynchronousExecutor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class UserMeasurementViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private UserMeasurementViewModel objectUnderTest;

    private NavigationController mockNavController;

    private MeasurementDao mockDao;

    @Before
    public void before() {
        SynchronousExecutor immediateExecutor = new SynchronousExecutor();
        mockDao = mock(MeasurementDao.class);
        mockNavController = mock(NavigationController.class);

        objectUnderTest = new UserMeasurementViewModel(immediateExecutor, mockDao, mockNavController);
    }

    @Test
    public void onRefreshMeasurements() throws Exception {
        final long fakeUserId = 1L;
        SimplePropertyChangedObserver progressSpinnerObserver = new SimplePropertyChangedObserver();
        objectUnderTest.shouldShowProgressSpinner.addOnPropertyChangedCallback(progressSpinnerObserver);

        Measurement[] measurements = new Measurement[] {new Measurement(), new Measurement()};
        MutableLiveData<Measurement[]> fakeLiveData = new MutableLiveData<>();
        fakeLiveData.setValue(measurements);
        when(mockDao.getUserMeasurements(fakeUserId)).thenReturn(fakeLiveData);

        objectUnderTest.getUserMeasurements().observeForever(mock(Observer.class));

        objectUnderTest.onRefreshMeasurements(fakeUserId);

        assertTrue(progressSpinnerObserver.propertyDidChange);
        assertFalse(objectUnderTest.shouldShowProgressSpinner.get());
    }

    @Test
    public void onCleared() throws Exception {

        objectUnderTest.shouldShowProgressSpinner.set(true);

        objectUnderTest.onCleared();

        assertFalse(objectUnderTest.shouldShowProgressSpinner.get());
        assertTrue(objectUnderTest.getUserMeasurements().getValue().length == 0);

    }

    @Test
    public void onAddMeasurementClick() throws Exception {
        User fakeUser = new User("fake", "user", 29, "M");
        doNothing().when(mockNavController).navigateToAddMeasurement(fakeUser);
        objectUnderTest.onAddMeasurementClick(fakeUser);

        verify(mockNavController, times(1)).navigateToAddMeasurement(fakeUser);

    }

}