package com.vlad.lesson4.presentation.ui.authorization;

import android.view.MotionEvent;

import com.vlad.lesson4.presentation.ui.base.MvpView;

public interface AuthorizationMvpView extends MvpView {

    void clickChangeVisibilityPassword();

    void setEntryButtonActive();

    void setEntryButtonInactive();

    void showProgressView();

    void hideProgressView();

    void clickEntryButton();

    void showAlertError();
}
