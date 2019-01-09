package com.vlad.lesson4.presentation.ui.help;

import com.vlad.lesson4.data.model.ItemForChooseCategoryHelp;
import com.vlad.lesson4.presentation.ui.base.MvpView;

import java.util.ArrayList;

public interface HelpMvpView extends MvpView {
    void onClickCategory();

    void showItemsCategory(ArrayList<ItemForChooseCategoryHelp> arrayList);

    void showLoadingError();

    void showProgressView();
}
