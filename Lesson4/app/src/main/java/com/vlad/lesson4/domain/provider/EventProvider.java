package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.data.remote.ApiService;

import java.util.List;

import io.reactivex.Single;

public class EventProvider {

    private final ApiService apiService;

    public EventProvider(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<List<Event>> getEvents() {
        return apiService.getEvents();
    }
}
