package com.abnormallydriven.architecturecomponentspost.common;


import android.os.Handler;
import android.support.annotation.NonNull;

import com.abnormallydriven.architecturecomponentspost.common.di.UI;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainThreadExecutor implements Executor {

    private final Handler uiHandler;

    @Inject
    public MainThreadExecutor(@UI Handler uiHandler){
        this.uiHandler = uiHandler;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        uiHandler.post(command);
    }

}
