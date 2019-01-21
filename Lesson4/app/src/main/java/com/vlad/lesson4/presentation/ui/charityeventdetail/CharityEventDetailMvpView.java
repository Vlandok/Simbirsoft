package com.vlad.lesson4.presentation.ui.charityeventdetail;

import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.MvpView;

public interface CharityEventDetailMvpView extends MvpView {
    Event getEvent(CharityEvent charityEvent, int id);

    void showLoadingError();

    void showProgressView();

    void showEventDetail(Event event);
}
