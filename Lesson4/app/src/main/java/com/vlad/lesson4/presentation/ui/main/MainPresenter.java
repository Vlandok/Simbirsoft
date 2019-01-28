package com.vlad.lesson4.presentation.ui.main;


import android.os.Bundle;

import com.vlad.lesson4.presentation.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainMvpView> {

    public void onCreate(Bundle savedInstanceState) {
        checkViewAttached();
        getMvpView().clickButtonBottomNav(savedInstanceState);
    }

    @Override
    protected void doUnsubscribe() {

    }
}
