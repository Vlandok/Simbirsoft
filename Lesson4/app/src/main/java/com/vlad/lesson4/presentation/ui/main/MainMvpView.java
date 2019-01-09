package com.vlad.lesson4.presentation.ui.main;

import com.vlad.lesson4.presentation.ui.base.MvpView;

public interface MainMvpView extends MvpView {
    void setTextInTextViewToolbar(String titleToolbar);

    void clickButtonBottomNav();
}
