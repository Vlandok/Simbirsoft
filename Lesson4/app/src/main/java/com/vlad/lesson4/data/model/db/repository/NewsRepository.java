package com.vlad.lesson4.data.model.db.repository;

import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class NewsRepository {

    private static NewsRepository INSTANCE = null;

    private NewsRepository() {
    }

    public static synchronized NewsRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsRepository();
        }
        return (INSTANCE);
    }

    public CharityEvent getEventCategoriesFromRealm() {
        try (Realm realm = Realm.getDefaultInstance()) {
            CharityEvent charityEvent = new CharityEvent();
            RealmList<Event> charityEventRealmList = new RealmList<>();
            RealmResults<Event> events = realm.where(Event.class).findAll();
            List<Event> eventsCopy = realm.copyFromRealm(events);
            if (eventsCopy != null) {
                charityEventRealmList.addAll(eventsCopy.subList(0, eventsCopy.size()));
                charityEvent.setEvents(charityEventRealmList);
            }
            return charityEvent;
        }
    }
}
