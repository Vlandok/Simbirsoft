package com.vlad.lesson4.data.model.db.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.EventCategories;
import com.vlad.lesson4.utils.JsonSupport;
import java.lang.reflect.Type;
import io.realm.Realm;

public interface RealmService {
    String FILE_JSON_CATEGORIES = "categories.json";
    String FILE_JSON_EVENTS = "events.json";

    class Creator {
        public static void saveToRealm(Context context) {
            try (Realm realm = Realm.getDefaultInstance()) {
                realm.beginTransaction();
                realm.deleteAll();
                realm.copyToRealm(jsonToEventCategories(context).getCategories());
                realm.copyToRealm(jsonToCharityEvent(context).getEvents());
                realm.commitTransaction();
            }
        }
    }

    static EventCategories jsonToEventCategories(Context context) {
        EventCategories categories = new EventCategories();
        String data = JsonSupport.loadJSONFromAsset(context, FILE_JSON_CATEGORIES);
        Type type = new TypeToken<EventCategories>() {
        }.getType();
        try {
            categories = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return categories;
    }

    static CharityEvent jsonToCharityEvent(Context context) {
        CharityEvent charityEvent = new CharityEvent();
        String data = JsonSupport.loadJSONFromAsset(context, FILE_JSON_EVENTS);
        Type type = new TypeToken<CharityEvent>() {
        }.getType();
        try {
            charityEvent = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return charityEvent;
    }
}

