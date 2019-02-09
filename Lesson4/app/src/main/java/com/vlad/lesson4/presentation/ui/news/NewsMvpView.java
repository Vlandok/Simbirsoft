package com.vlad.lesson4.presentation.ui.news;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.MvpView;

import java.util.List;

public interface NewsMvpView extends MvpView {
    void showCharityEvents(List<Event> arrayListEvent);

    void showLoadingError();

    void showProgressView();

    void onClickEvent();

    void onClickErrorButton();

    List<Event> getListEventsFromJson();
}
