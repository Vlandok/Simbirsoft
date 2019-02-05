package com.vlad.lesson4.presentation.ui.сharityevents;

import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.List;

public class CharityEventsPresenter extends BasePresenter<CharityEventsMvpView> {

    public void onCreate() {
        checkViewAttached();
        getCharityEvents();
    }

    @Override
    protected void doUnsubscribe() {

    }

    private void getCharityEvents() {
        checkViewAttached();
        CharityEventsTask charityEventsTask = new CharityEventsTask(getMvpView(), this);
        charityEventsTask.execute();
    }

    void showEvents(CharityEventsMvpView mvpView, CharityEvent charityEvent) {
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
