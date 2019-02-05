package com.vlad.lesson4.presentation.ui.charityeventdetail;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

public class CharityEventDetailPresenter extends BasePresenter<CharityEventDetailMvpView> {

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

    void showEventsDetail(CharityEventDetailMvpView mvpView, Event event) {
        if (event == null) {
            mvpView.showLoadingError();
        } else {
            mvpView.showEventDetail(event);
        }
    }
}
