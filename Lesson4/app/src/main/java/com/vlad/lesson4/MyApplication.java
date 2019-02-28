package com.vlad.lesson4;

import android.app.Activity;
import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.vlad.lesson4.di.component.ApplicationComponent;
//import com.vlad.lesson4.di.component.DaggerApplicationComponent;
import com.vlad.lesson4.di.component.DaggerApplicationComponent;
import com.vlad.lesson4.di.module.FirebaseModule;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static MyApplication getInstance(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());

        applicationComponent = DaggerApplicationComponent.builder()
                .firebaseModule(new FirebaseModule())
                .build();


//        applicationComponents = ApplicationComponents.getInstance(this);
    }

//    public ApplicationComponents getApplicationComponents() {
//        return applicationComponents;
//    }
}
