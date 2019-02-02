package com.vlad.lesson4.presentation.ui.сharityevents;

import android.content.Context;

import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class CharityEventsPresenter extends BasePresenter<CharityEventsMvpView> {

    private CharityEvent charityEvent = new CharityEvent();
    private RealmList<Event> charityEventRealmList = new RealmList<>();

    public void onCreate(Context context) {
        checkViewAttached();
        getCharityEvents(context);
    }

    @Override
    protected void doUnsubscribe() {

    }

    private void getCharityEvents(Context context) {
        checkViewAttached();
        CharityEventsTask charityEventsTask = new CharityEventsTask(context, getMvpView(), this);
        charityEventsTask.execute();
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

    void showEvents(CharityEventsMvpView mvpView) {
        checkViewAttached();
        if (charityEvent == null) {
            mvpView.showLoadingError();
        } else {
            mvpView.setTitleToolbar();
            List<Event> events = getMvpView().getEventsCategory(charityEvent.getEvents());
            mvpView.showCharityEvents(events);
            mvpView.onClickEvent();
        }
    }
}
