package com.vlad.lesson4.data.remote.api;

import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.data.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface FirebaseApi {
    @GET("categories.json")
    Single<List<Category>> getCategories();

    @GET("events.json")
    Single<List<Event>> getEvents();

    @GET("users.json")
    Single<List<User>> getUsers();

    @GET("users.json")
    User getCurrentUser();
}
