package com.vlad.lesson4.presentation.ui.searchresultnko;

import com.vlad.lesson4.data.model.SearchResultsNko;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Random;

import static com.vlad.lesson4.presentation.ui.search.FragmentPagerAdapter.ALL_CHARACTERS;

public class SearchResultNkoPresenter extends BasePresenter<SearchResultNkoMvpView> {

    public static int LENGTH_STRING_RANDOM = 10;

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
        ArrayList<SearchResultsNko> arrayListSearchResults = initSearchResults();
        if (arrayListSearchResults == null) {
            getMvpView().showLoadingError();
        } else {
            getMvpView().showSearchResultNko(arrayListSearchResults);
            getMvpView().clickItemSearchResultNko();
        }
    }

    public ArrayList<SearchResultsNko> initSearchResults() {
        ArrayList<SearchResultsNko> listItems = new ArrayList<>();
        for (int i = 0; i < LENGTH_STRING_RANDOM; i++) {
            listItems.add(new SearchResultsNko(generateString(random, ALL_CHARACTERS,
                    LENGTH_STRING_RANDOM)));
        }
        return listItems;
    }

    @Override
    protected void doUnsubscribe() {

    }
}
