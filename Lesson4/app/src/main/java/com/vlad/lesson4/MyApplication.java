package com.vlad.lesson4;

import android.app.Application;

import com.vlad.lesson4.di.ApplicationComponents;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    private ApplicationComponents applicationComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        applicationComponents = ApplicationComponents.getInstance(this);
        Realm.setDefaultConfiguration(config);
    }

    public ApplicationComponents getApplicationComponents() {
        return applicationComponents;
    }
}
