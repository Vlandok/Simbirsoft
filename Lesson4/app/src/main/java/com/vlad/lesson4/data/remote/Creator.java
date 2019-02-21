package com.vlad.lesson4.data.remote;

import com.vlad.lesson4.data.remote.api.FirebaseApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Creator {
    private static String ENDPOINT = "https://lesson4-baada.firebaseio.com/";

    public static FirebaseApi newApiService() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.addInterceptor(new FirebaseUserIdTokenInterceptor());

        OkHttpClient client = okBuilder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(FirebaseApi.class);
    }
}
