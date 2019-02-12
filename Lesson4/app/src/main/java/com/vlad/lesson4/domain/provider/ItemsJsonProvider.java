package com.vlad.lesson4.domain.provider;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.data.model.EventCategories;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.reflect.Type;
import java.util.List;

public class ItemsJsonProvider {

    private static final String FILE_JSON_CATEGORIES = "categories.json";
    private final static String FILE_JSON_EVENTS = "events.json";

    private Context context;

    public ItemsJsonProvider(Context context) {
        this.context = context;
    }

    public List<Category> getListCategoriesFromJson() {
        EventCategories categories = new EventCategories();
        String data = JsonSupport.loadJSONFromAsset(context, FILE_JSON_CATEGORIES);
        Type type = new TypeToken<EventCategories>() {
        }.getType();
        try {
            categories = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return categories != null ? categories.getCategories() : null;
    }

    public List<Event> getListEventsCategoryFromJson() {
        CharityEvent charityEvent = new CharityEvent();
        String data = JsonSupport.loadJSONFromAsset(context, FILE_JSON_EVENTS);
        Type type = new TypeToken<CharityEvent>() {
        }.getType();
        try {
            charityEvent = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return charityEvent != null ? charityEvent.getEvents() : null;
    }
}
