package com.vlad.lesson4.presentation.ui.help;

import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.presentation.ui.base.MvpView;

import java.util.List;

public interface HelpMvpView extends MvpView {
    void onClickCategory();

    void showItemsCategory(List<Category> arrayList);

    void showLoadingError();

    void showProgressView();

}
