package com.vlad.lesson4.presentation.ui.help;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.vlad.lesson4.data.model.Category;

import java.util.List;

public interface HelpMvpView extends MvpView {
    void onClickCategory();

    void showItemsCategory(List<Category> arrayList);

    void showLoadingError();

    void showProgressView();

    void onClickErrorButton();
}
