package com.vlad.lesson4.data.model.db.repository;

import com.vlad.lesson4.data.model.Event;

import io.realm.Realm;

public class CharityEventDetailRepository {

    private static CharityEventDetailRepository INSTANCE = null;

    private CharityEventDetailRepository() {
    }

    public static synchronized CharityEventDetailRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CharityEventDetailRepository();
        }
        return (INSTANCE);
    }

    public Event getEventFromRealm(int idEvent) {
        try (Realm realm = Realm.getDefaultInstance()) {
            Event eventCopy = new Event();
            Event event = realm.where(Event.class)
                    .equalTo("id", idEvent).findFirst();
            if (event != null) {
                eventCopy = realm.copyFromRealm(event);
            }
            return eventCopy;
        }
    }
}
