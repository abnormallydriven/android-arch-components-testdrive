package com.abnormallydriven.architecturecomponentspost;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTestUtil {

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T getValue(final LiveData<T> liveData){
        final Object[] data = new Object[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Observer<T> liveDataObserver = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                data[0] = t;
                countDownLatch.countDown();
                liveData.removeObserver(this);
            }
        };

        liveData.observeForever(liveDataObserver);
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return (T) data[0];
    }
}
