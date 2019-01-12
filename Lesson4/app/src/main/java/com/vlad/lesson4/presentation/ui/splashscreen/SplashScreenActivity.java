package com.vlad.lesson4.presentation.ui.splashscreen;

import android.os.Bundle;

import com.vlad.lesson4.R;
import com.vlad.lesson4.presentation.ui.base.BaseActivity;
import com.vlad.lesson4.presentation.ui.main.MainActivity;

public class SplashScreenActivity extends BaseActivity implements SplashScreenMvpView {

    public final static int SECONDS_SLEEP = 2;

    private SplashScreenPresenter splashScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreenPresenter = getApplicationComponents().provideSplashScreenPresenter();
        splashScreenPresenter.attachView(this);
        splashScreenPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        splashScreenPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void startMainActivity() {
        startActivity(MainActivity.createStartIntent(SplashScreenActivity.this));
        finish();
    }
}
