package com.vlad.lesson4.presentation.ui.main;

import android.os.Bundle;

import com.vlad.lesson4.presentation.ui.base.MvpView;

public interface MainMvpView extends MvpView {
    void setTextInTextViewToolbar(String titleToolbar);

    void clickButtonBottomNav(Bundle savedInstanceState);
}