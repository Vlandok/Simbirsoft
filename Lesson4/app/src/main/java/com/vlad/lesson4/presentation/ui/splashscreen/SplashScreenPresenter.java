package com.vlad.lesson4.presentation.ui.splashscreen;

import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.presentation.ui.main.MainActivity;

import java.util.concurrent.TimeUnit;

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
                TimeUnit.SECONDS.sleep(MainActivity.TWO);
                getMvpView().startMainActivity();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}
