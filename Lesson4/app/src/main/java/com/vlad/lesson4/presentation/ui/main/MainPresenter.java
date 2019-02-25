package com.vlad.lesson4.presentation.ui.main;


import android.os.Bundle;

import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import rx.Observable;

public class MainPresenter extends BasePresenter<MainMvpView> {

    public void onCreate(Bundle savedInstanceState) {
        checkViewAttached();
        getMvpView().clickButtonBottomNav(savedInstanceState);
    }

//    private void getItemMenuSelected() {
//        Observable<>
//    }

    @Override
    protected void doUnsubscribe() {

    }
}
