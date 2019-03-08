package com.vlad.lesson4;

import android.app.Activity;
import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.vlad.lesson4.di.component.ActivityComponent;
import com.vlad.lesson4.di.component.ApplicationComponent;
import com.vlad.lesson4.di.component.DaggerApplicationComponent;
import com.vlad.lesson4.di.module.ActivityModule;
import com.vlad.lesson4.di.module.FirebaseModule;

import androidx.appcompat.app.AppCompatActivity;

public class MyApplication extends Application {

    public static ApplicationComponent applicationComponent;
    protected static MyApplication instance;
    private ActivityComponent activityComponent;

    public static MyApplication getInstance(Activity activity) {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FirebaseApp.initializeApp(getApplicationContext());

        applicationComponent = DaggerApplicationComponent.builder()
                .firebaseModule(new FirebaseModule())
                .build();
    }

    public ActivityComponent plusActivityComponent(AppCompatActivity activity) {
        if (activityComponent == null) {
            activityComponent = applicationComponent.plusActivityComponent(new ActivityModule(activity));
        }
        return activityComponent;
    }

    public void clearActivityComponent() {
        activityComponent = null;
    }
}
