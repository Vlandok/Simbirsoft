package com.vlad.lesson4.presentation.ui.charityeventdetail;

import android.content.Context;

import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailActivity.DEFAULT_VALUE;

public class CharityEventDetailPresenter extends BasePresenter<CharityEventDetailMvpView> {

    private CharityEvent charityEvent = new CharityEvent();
    private RealmList<Event> charityEventRealmList = new RealmList<>();

    public void onCreate(Context context, int id) {
        checkViewAttached();
        getEvent(context, id);
    }

    @Override
    protected void doUnsubscribe() {

    }

    private void getEvent(Context context, int id) {
        checkViewAttached();
        CharityEventDetailTask charityEventDetailTask
                = new CharityEventDetailTask(context, getMvpView(), id, this);
        charityEventDetailTask.execute();
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

    void showEventsDetail(CharityEventDetailMvpView mvpView, Integer id) {
        if (charityEvent == null && id == DEFAULT_VALUE) {
            mvpView.showLoadingError();
        } else {
            Event event = getMvpView().getEvent(charityEvent, id);
            mvpView.showEventDetail(event);
        }
    }
}
