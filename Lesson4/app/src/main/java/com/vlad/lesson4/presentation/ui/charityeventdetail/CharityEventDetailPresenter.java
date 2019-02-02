package com.vlad.lesson4.presentation.ui.charityeventdetail;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import io.realm.Realm;

public class CharityEventDetailPresenter extends BasePresenter<CharityEventDetailMvpView> {

    private Event eventCopy = new Event();

    public void onCreate(int id) {
        checkViewAttached();
        getEvent(id);
    }

    @Override
    protected void doUnsubscribe() {

    }

    private void getEvent(int id) {
        checkViewAttached();
        CharityEventDetailTask charityEventDetailTask
                = new CharityEventDetailTask(getMvpView(), id, this);
        charityEventDetailTask.execute();
    }

    void getEventFromRealm(int idEvent) {
        try (Realm realm = Realm.getDefaultInstance()) {
            Event event = realm.where(Event.class)
                    .equalTo("id", idEvent).findFirst();
            if (event != null) {
                eventCopy = realm.copyFromRealm(event);
            }
        }
    }

    void showEventsDetail(CharityEventDetailMvpView mvpView) {
        if (eventCopy == null) {
            mvpView.showLoadingError();
        } else {
            mvpView.showEventDetail(eventCopy);
        }
    }
}
