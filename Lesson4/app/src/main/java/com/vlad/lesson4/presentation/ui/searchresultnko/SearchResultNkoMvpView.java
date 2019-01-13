package com.vlad.lesson4.presentation.ui.searchresultnko;

import com.vlad.lesson4.data.model.SearchResultsNko;
import com.vlad.lesson4.presentation.ui.base.MvpView;

import java.util.ArrayList;

public interface SearchResultNkoMvpView extends MvpView {

    void showSearchResultNko(ArrayList<SearchResultsNko> arrayListSearchResultNko);

    void showLoadingError();

    void showProgressView();

    void clickItemSearchResultNko();
}
