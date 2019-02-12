package com.vlad.lesson4.presentation.ui.сharityevents;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.MvpView;

import java.util.List;

public interface CharityEventsMvpView extends MvpView {
    void showCharityEvents(List<Event> arrayListEvent);

    void showLoadingError();

    void onClickErrorButton();

    void showProgressView();

    void onClickEvent();

    void setTitleToolbar();

}
