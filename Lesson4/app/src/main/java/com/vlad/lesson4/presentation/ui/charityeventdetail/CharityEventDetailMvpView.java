package com.vlad.lesson4.presentation.ui.charityeventdetail;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.MvpView;

import java.util.List;

public interface CharityEventDetailMvpView extends MvpView {

    void showLoadingError();

    void onClickErrorButton();

    void showProgressView();

    void showEventDetail(Event event);

    List<Event> getListEventsCategoryFromJson();

    Event getEventFromListEvents(int id, List<Event> events);
}
