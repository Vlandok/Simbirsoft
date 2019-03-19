package com.vlad.lesson4.presentation.ui.searchresultnko;

import com.arellomobile.mvp.MvpView;
import com.vlad.lesson4.data.model.SearchResultsNko;

import java.util.ArrayList;

public interface SearchResultNkoMvpView extends MvpView {

    void showSearchResultNko(ArrayList<SearchResultsNko> arrayListSearchResultNko);

    void showLoadingError();

    void showProgressView();

    void clickItemSearchResultNko();
}
