package com.vlad.lesson4.di.module;

import com.vlad.lesson4.data.remote.FirebaseUserIdTokenInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = ContextModule.class)
public class OkHttpClientModule {
    @Singleton
    @Provides
    OkHttpClient okHttpClient(FirebaseUserIdTokenInterceptor firebaseUserIdTokenInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(firebaseUserIdTokenInterceptor)
                .build();
    }

    @Singleton
    @Provides
    FirebaseUserIdTokenInterceptor firebaseUserIdTokenInterceptor() {
        return new FirebaseUserIdTokenInterceptor();
    }

}
