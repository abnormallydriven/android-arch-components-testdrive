package com.abnormallydriven.architecturecomponentspost.addmeasurements;

import com.abnormallydriven.architecturecomponentspost.common.data.MeasurementDao;
import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.utils.SimplePropertyChangedObserver;
import com.abnormallydriven.architecturecomponentspost.utils.SynchronousExecutor;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddMeasurementsViewModelTest {

    @Test
    public void testOnSaveMeasurementForUserClick(){
        SynchronousExecutor immediateExecutor = new SynchronousExecutor();
        MeasurementDao mockMeasurementDao = mock(MeasurementDao.class);
        final Measurement fakeMeasurements = new Measurement();

        AddMeasurementsViewModel objectUnderTest = new AddMeasurementsViewModel(immediateExecutor, immediateExecutor, mockMeasurementDao);

        SimplePropertyChangedObserver progressSpinnerObserver = new SimplePropertyChangedObserver();
        objectUnderTest.shouldShowProgressSpinner.addOnPropertyChangedCallback(progressSpinnerObserver);

        SimplePropertyChangedObserver didShowSaveMessageObserver = new SimplePropertyChangedObserver();
        objectUnderTest.shouldShowSaveSuccessMessage.addOnPropertyChangedCallback(didShowSaveMessageObserver);

        when(mockMeasurementDao.insertMeasurements(fakeMeasurements)).thenReturn(1L);

        //act
        objectUnderTest.onSaveMeasurementForUserClick(fakeMeasurements);


        verify(mockMeasurementDao, times(1)).insertMeasurements(fakeMeasurements);
        assertTrue(progressSpinnerObserver.propertyDidChange);
        assertTrue(didShowSaveMessageObserver.propertyDidChange);
        assertFalse(objectUnderTest.shouldShowProgressSpinner.get());

    }

    @Test
    public void onClearedShouldResetViewModel(){
        SynchronousExecutor immediateExecutor = new SynchronousExecutor();
        MeasurementDao mockMeasurementDao = mock(MeasurementDao.class);

        AddMeasurementsViewModel objectUnderTest = new AddMeasurementsViewModel(immediateExecutor, immediateExecutor, mockMeasurementDao);

        objectUnderTest.shouldShowSaveSuccessMessage.set(true);
        objectUnderTest.shouldShowProgressSpinner.set(true);

        objectUnderTest.onCleared();


        assertFalse(objectUnderTest.shouldShowSaveSuccessMessage.get());
        assertFalse(objectUnderTest.shouldShowProgressSpinner.get());


    }

}