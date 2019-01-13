package com.vlad.lesson4;

import android.app.Application;

import com.vlad.lesson4.di.ApplicationComponents;

public class MyApplication extends Application {

    private ApplicationComponents applicationComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponents = ApplicationComponents.getInstance(this);
    }

    public ApplicationComponents getApplicationComponents() {
        return applicationComponents;
    }
}
