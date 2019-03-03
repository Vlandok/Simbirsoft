package com.vlad.lesson4.presentation.ui.searchresultevents;

import com.arellomobile.mvp.InjectViewState;
import com.vlad.lesson4.data.model.SearchResults;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.presentation.ui.base.BasePresenterMoxy;

import java.util.ArrayList;
import java.util.Random;

import static com.vlad.lesson4.presentation.ui.search.FragmentPagerAdapter.ALL_CHARACTERS;

@InjectViewState
public class SearchResultEventsPresenter extends BasePresenterMoxy<SearchResultEventsMvpView> {

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
        getSearchResults();
    }

    private void getSearchResults() {
        getViewState().showProgressView();
        ArrayList<SearchResults> arrayListSearchResults = initSearchResults();
        if (arrayListSearchResults == null) {
            getViewState().showLoadingError();
        } else {
            getViewState().showSearchResult(arrayListSearchResults);
            getViewState().clickItemSearchResult();
        }
    }

    public ArrayList<SearchResults> initSearchResults() {
        ArrayList<SearchResults> listItems = new ArrayList<>();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < LENGTH_STRING_RANDOM; i++) {
                listItems.add(new SearchResults(generateString(random, ALL_CHARACTERS,
                        LENGTH_STRING_RANDOM)));
            }
        });
        thread.start();
        return listItems;
    }

    @Override
    protected void doUnsubscribe() {

    }
}
