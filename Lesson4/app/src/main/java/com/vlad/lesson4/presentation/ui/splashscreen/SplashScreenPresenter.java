package com.vlad.lesson4.presentation.ui.splashscreen;

import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import static com.vlad.lesson4.presentation.ui.splashscreen.SplashScreenActivity.SECONDS_SLEEP;

public class SplashScreenPresenter extends BasePresenter<SplashScreenMvpView> {

    public void onCreate() {
        startMainActivity();
    }

    @Override
    protected void doUnsubscribe() {

    }

    private void startMainActivity() {
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(SECONDS_SLEEP);
                getMvpView().startMainActivity();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}
