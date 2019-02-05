package com.vlad.lesson4.presentation.ui.splashscreen;

import com.vlad.lesson4.presentation.ui.base.BasePresenter;

public class SplashScreenPresenter extends BasePresenter<SplashScreenMvpView> {

    public void onCreate() {
        saveDataInRealmAsync();
    }

    @Override
    protected void doUnsubscribe() {
    }

    private void saveDataInRealmAsync() {
        SplashScreenTask splashScreenTask
                = new SplashScreenTask(this, getMvpView());
        splashScreenTask.execute();
    }
}
