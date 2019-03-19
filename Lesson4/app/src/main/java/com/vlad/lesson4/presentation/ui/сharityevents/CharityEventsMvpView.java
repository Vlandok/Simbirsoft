package com.vlad.lesson4.presentation.ui.сharityevents;

import com.arellomobile.mvp.MvpView;
import com.vlad.lesson4.data.model.Event;

import java.util.List;

public interface CharityEventsMvpView extends MvpView {
    void showCharityEvents(List<Event> arrayListEvent);

    void showLoadingError();

    void onClickErrorButton();

    void showProgressView();

    void onClickEvent();

    void setTitleToolbar();

}
