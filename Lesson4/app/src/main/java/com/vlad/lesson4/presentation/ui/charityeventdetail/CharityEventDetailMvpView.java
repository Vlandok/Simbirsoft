package com.vlad.lesson4.presentation.ui.charityeventdetail;

import com.arellomobile.mvp.MvpView;
import com.vlad.lesson4.data.model.Event;

public interface CharityEventDetailMvpView extends MvpView {

    void showLoadingError();

    void onClickErrorButton();

    void showProgressView();

    void showEventDetail(Event event);
}
