package com.vlad.lesson4.data.remote.api;

import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.model.Event;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface FirebaseApi {
    @GET("categories.json")
    Single<List<Category>> getCategories();

    @GET("events.json")
    Single<List<Event>> getEvents();
}
