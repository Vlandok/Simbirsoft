package com.vlad.lesson4.presentation.ui.news;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class NewsPresenter extends BasePresenter<NewsMvpView> {

    private CharityEvent charityEvent = new CharityEvent();
    private RealmList<Event> charityEventRealmList = new RealmList<>();

    public void onCreate(Context context) {
        checkViewAttached();
        getCharityEvents(context);
    }

    private void getCharityEvents(Context context) {
        checkViewAttached();
        NewsTask newsTask = new NewsTask(context, getMvpView(), this);
        newsTask.execute();
    }

    @Override
    protected void doUnsubscribe() {

    }

    void getEventCategoriesFromRealm() {
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<Event> events = realm.where(Event.class).findAll();
            List<Event> eventsCopy = realm.copyFromRealm(events);
            if (eventsCopy != null) {
                charityEventRealmList.addAll(eventsCopy.subList(0, eventsCopy.size()));
                charityEvent.setEvents(charityEventRealmList);
            } else {
                charityEvent = null;
            }
        }
    }

    void showNews(NewsMvpView mvpView) {
        if (charityEvent == null) {
            mvpView.showLoadingError();
            mvpView.onClickErrorButton();
        } else {
            mvpView.showCharityEvents(charityEvent.getEvents());
            mvpView.onClickEvent();
        }
    }
}
