package com.vlad.lesson4.presentation.ui.searchresultevents;

import com.vlad.lesson4.data.model.SearchResults;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Random;

import static com.vlad.lesson4.presentation.ui.search.FragmentPagerAdapter.ALL_CHARACTERS;

public class SearchResultEventsPresenter extends BasePresenter<SearchResultEventsMvpView> {

    public static int TEN = 10;

    private ArrayList<SearchResults> listItems;
    private Random random = new Random();

    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public void onCreate() {
        checkViewAttached();
        getSearchResults();
    }

    private void getSearchResults() {
        checkViewAttached();
        getMvpView().showProgressView();
        ArrayList<SearchResults> arrayListSearchResults = initSearchResults();
        if (arrayListSearchResults == null) {
            getMvpView().showLoadingError();
        } else {
            getMvpView().showSearchResult(arrayListSearchResults);
            getMvpView().clickItemSearchResult();
        }
    }

    private ArrayList<SearchResults> initSearchResults() {
        listItems = new ArrayList<>();
        for (int i = 0; i < TEN; i++) {
            listItems.add(new SearchResults(generateString(random, ALL_CHARACTERS, TEN)));
        }
        return listItems;
    }

    @Override
    protected void doUnsubscribe() {

    }
}
