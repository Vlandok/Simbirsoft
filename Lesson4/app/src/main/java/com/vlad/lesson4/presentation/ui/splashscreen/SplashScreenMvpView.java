package com.vlad.lesson4.presentation.ui.splashscreen;

import com.arellomobile.mvp.MvpView;

public interface SplashScreenMvpView extends MvpView {
    void startMainActivity();

    void showLoadingError();

    void showProgressView();
}
