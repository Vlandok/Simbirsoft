package com.vlad.lesson4.data.remote;

import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;
import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.model.Event;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {
    String ENDPOINT = "https://lesson4-baada.firebaseio.com/";

    class Creator {
        public static ApiService newApiService(Context context) {

            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

            okBuilder.addInterceptor(new ChuckInterceptor(context));

            OkHttpClient client = okBuilder.build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(ApiService.class);
        }
    }

    @GET("categories.json")
    Single<List<Category>> getCategories();

    @GET("events.json")
    Single<List<Event>> getEvents();

}
