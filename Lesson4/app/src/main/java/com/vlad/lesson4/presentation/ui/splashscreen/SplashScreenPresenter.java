package com.vlad.lesson4.presentation.ui.splashscreen;

import com.arellomobile.mvp.InjectViewState;
import com.vlad.lesson4.presentation.ui.base.BasePresenterMoxy;

@InjectViewState
public class SplashScreenPresenter extends BasePresenterMoxy<SplashScreenMvpView> {

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
