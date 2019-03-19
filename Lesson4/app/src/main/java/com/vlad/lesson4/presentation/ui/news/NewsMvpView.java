package com.vlad.lesson4.presentation.ui.news;

import com.arellomobile.mvp.MvpView;
import com.vlad.lesson4.data.model.Event;

import java.util.List;

public interface NewsMvpView extends MvpView {
    void showCharityEvents(List<Event> arrayListEvent);

    void showLoadingError();

    void showProgressView();

    void onClickEvent();

    void onClickErrorButton();
}
