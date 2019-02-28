package com.vlad.lesson4.di.component;

import com.vlad.lesson4.data.remote.api.FirebaseApi;
import com.vlad.lesson4.di.module.FirebaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FirebaseModule.class)
public interface ApplicationComponent {
    FirebaseApi getFirebaseApi();
}
