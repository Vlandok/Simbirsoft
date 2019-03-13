package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.data.remote.api.FirebaseApi;

import java.util.List;

import io.reactivex.Single;

public class EventProvider extends BaseProvider {

    public EventProvider(FirebaseApi firebaseApi) {
        super(firebaseApi);
    }

    public Single<List<Event>> getEvents() {
        return firebaseApi.getEvents();
    }
}
