package com.vlad.lesson4.presentation.ui.main;


import com.vlad.lesson4.presentation.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainMvpView> {


    public void onCreate() {
        checkViewAttached();
        getMvpView().clickButtonBottomNav();
    }

    @Override
    protected void doUnsubscribe() {

    }
}
