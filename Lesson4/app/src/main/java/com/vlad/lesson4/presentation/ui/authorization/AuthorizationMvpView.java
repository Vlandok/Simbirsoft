package com.vlad.lesson4.presentation.ui.authorization;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface AuthorizationMvpView extends MvpView {

    void clickChangeVisibilityPassword();

    void setEntryButtonActive();

    void setEntryButtonInactive();

    void showProgressView();

    void hideProgressView();

    @StateStrategyType(SkipStrategy.class)
    void clickEntryButton();

    @StateStrategyType(SkipStrategy.class)
    void showAlertError();
}
