package com.abnormallydriven.architecturecomponentspost;


public final class DaggerInjector {

    private static ApplicationComponent appComponent;

    static void initializeComponent(ApplicationComponent applicationComonent) {
        appComponent = applicationComonent;
    }

    static ApplicationComponent getAppComponent() {
        if (null == appComponent) {
            throw new NullPointerException("Application component was null when requested");
        }
        return appComponent;
    }

    private DaggerInjector() {

    }

}
