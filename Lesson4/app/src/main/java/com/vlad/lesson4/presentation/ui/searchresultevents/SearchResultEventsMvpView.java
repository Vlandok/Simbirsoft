package com.vlad.lesson4.presentation.ui.searchresultevents;

import com.arellomobile.mvp.MvpView;
import com.vlad.lesson4.data.model.SearchResults;

import java.util.ArrayList;

public interface SearchResultEventsMvpView extends MvpView {

    void showSearchResult(ArrayList<SearchResults> arrayListSearchResult);

    void showLoadingError();

    void showProgressView();

    void clickItemSearchResult();
}
