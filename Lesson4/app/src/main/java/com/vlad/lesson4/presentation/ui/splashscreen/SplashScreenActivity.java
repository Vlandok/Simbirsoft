package com.vlad.lesson4.presentation.ui.splashscreen;

import android.os.Bundle;
import android.widget.ViewFlipper;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vlad.lesson4.R;
import com.vlad.lesson4.presentation.ui.base.BaseActivityMoxy;
import com.vlad.lesson4.presentation.ui.main.MainActivity;

public class SplashScreenActivity extends BaseActivityMoxy implements SplashScreenMvpView {

    public final static int SECONDS_SLEEP = 2;

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_ERROR = 1;

    @InjectPresenter
    public SplashScreenPresenter splashScreenPresenter;

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        viewFlipper = findViewById(R.id.viewFlipperSplashScreen);
    }

    @Override
    public void startMainActivity() {
        startActivity(MainActivity.createStartIntent(SplashScreenActivity.this));
        finish();
    }

    @Override
    public void showLoadingError() {
        viewFlipper.setDisplayedChild(VIEW_ERROR);
    }

    @Override
    public void showProgressView() {
        viewFlipper.setDisplayedChild(VIEW_LOADING);
    }

}
