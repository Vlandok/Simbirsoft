package com.vlad.lesson4.presentation.ui.splashscreen;

import android.os.Bundle;
import android.widget.ViewFlipper;

import com.vlad.lesson4.R;
import com.vlad.lesson4.di.component.ActivityComponent;
import com.vlad.lesson4.presentation.ui.base.BaseActivity;
import com.vlad.lesson4.presentation.ui.main.MainActivity;

import javax.inject.Inject;

//import com.vlad.lesson4.di.component.DaggerActivityComponent;

public class SplashScreenActivity extends BaseActivity implements SplashScreenMvpView {

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_ERROR = 1;
    private ActivityComponent activityComponent;

    public final static int SECONDS_SLEEP = 2;

    @Inject
    SplashScreenPresenter splashScreenPresenter;

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        viewFlipper = findViewById(R.id.viewFlipperSplashScreen);

        activityComponent = getActivityComponent();
        activityComponent.inject(this);
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

    @Override
    public void showLoadingError() {
        viewFlipper.setDisplayedChild(VIEW_ERROR);
    }

    @Override
    public void showProgressView() {
        viewFlipper.setDisplayedChild(VIEW_LOADING);
    }

}
