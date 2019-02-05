package com.vlad.lesson4.presentation.ui.splashscreen;

import com.vlad.lesson4.presentation.ui.base.MvpView;

public interface SplashScreenMvpView extends MvpView {
    void startMainActivity();

    void showLoadingError();

    void showProgressView();
}
