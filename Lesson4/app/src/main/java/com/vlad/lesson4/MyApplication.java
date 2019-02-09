package com.vlad.lesson4;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.vlad.lesson4.di.ApplicationComponents;

public class MyApplication extends Application {

    private ApplicationComponents applicationComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
        applicationComponents = ApplicationComponents.getInstance(this);
    }

    public ApplicationComponents getApplicationComponents() {
        return applicationComponents;
    }
}
