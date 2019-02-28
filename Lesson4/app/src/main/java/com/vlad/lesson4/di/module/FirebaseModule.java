package com.vlad.lesson4.di.module;

import com.vlad.lesson4.data.remote.api.FirebaseApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class FirebaseModule {
    private static final String BASE_URL = "https://lesson4-baada.firebaseio.com/";

    @Singleton
    @Provides
    FirebaseApi firebaseApi(Retrofit retrofit) {
        return retrofit.create(FirebaseApi.class);
    }

    @Singleton
    @Provides
    Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }
}
