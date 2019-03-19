package com.vlad.lesson4.presentation.ui.splashscreen;

import com.arellomobile.mvp.InjectViewState;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

@InjectViewState
public class SplashScreenPresenter extends BasePresenter<SplashScreenMvpView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        saveDataInRealmAsync();
    }

    private void saveDataInRealmAsync() {
        SplashScreenTask splashScreenTask
                = new SplashScreenTask(this, getViewState());
        splashScreenTask.execute();
    }
}
