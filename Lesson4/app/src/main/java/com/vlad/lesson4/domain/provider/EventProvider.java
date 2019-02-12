package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.data.remote.ApiService;
import com.vlad.lesson4.data.remote.api.FirebaseApi;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

public class EventProvider {

    private final FirebaseApi firebaseApi;

    public EventProvider(FirebaseApi firebaseApi) {
        this.firebaseApi = firebaseApi;
    }

    public Single<List<Event>> getEvents() {
        return firebaseApi.getEvents();
    }

    public <T> SingleTransformer<T, T> applyScheduler() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io());
    }
}
