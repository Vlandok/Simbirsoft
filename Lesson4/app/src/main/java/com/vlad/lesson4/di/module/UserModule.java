package com.vlad.lesson4.di.module;

import com.vlad.lesson4.data.remote.api.FirebaseApi;
import com.vlad.lesson4.domain.provider.UserProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    UserProvider userProvider(FirebaseApi firebaseApi) {
        return new UserProvider(firebaseApi);
    }
}
