package com.vlad.lesson4.di.module;

import com.vlad.lesson4.data.remote.api.FirebaseApi;
import com.vlad.lesson4.domain.provider.EventProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class EventModule {

    @Provides
    EventProvider eventProvider(FirebaseApi firebaseApi) {
        return new EventProvider(firebaseApi);
    }
}
