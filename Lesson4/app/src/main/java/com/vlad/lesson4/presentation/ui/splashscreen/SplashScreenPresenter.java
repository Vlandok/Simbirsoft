package com.vlad.lesson4.presentation.ui.splashscreen;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.EventCategories;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.reflect.Type;

import io.realm.Realm;

public class SplashScreenPresenter extends BasePresenter<SplashScreenMvpView> {

    private static final String FILE_JSON_CATEGORIES = "categories.json";
    private static final String FILE_JSON_EVENTS = "events.json";

    private EventCategories categories;
    private CharityEvent charityEvent;

    public void onCreate(Context context) {
        saveDataInRealmAsync(context);
    }

    @Override
    protected void doUnsubscribe() {
    }

    private void saveDataInRealmAsync(Context context) {
        SplashScreenTask splashScreenTask
                = new SplashScreenTask(this, context, getMvpView());
        splashScreenTask.execute();
    }

    void saveToRealm(Context context) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.deleteAll();
            realm.copyToRealm(jsonToEventCategories(context).getCategories());
            realm.copyToRealm(jsonToCharityEvent(context).getEvents());
            realm.commitTransaction();
        }
    }

    private EventCategories jsonToEventCategories(Context context) {
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

    private CharityEvent jsonToCharityEvent(Context context) {
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
