package com.vlad.lesson4.presentation.ui.searchresultevents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.SearchResults;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;
import com.vlad.lesson4.presentation.ui.search.Updatable;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultEventsFragment extends BaseFragment implements SearchResultEventsMvpView, Updatable {

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    private SearchResultEventsPresenter searchResultPresenter;
    private RecyclerView recyclerView;
    private SearchResultEventsAdapter searchResultAdapter;
    private ViewFlipper viewFlipper;

    public SearchResultEventsFragment() {

    }

    public static SearchResultEventsFragment getInstance() {
        return new SearchResultEventsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchResultPresenter = getApplicationComponents().provideSearchResultEventsPresenter();
        searchResultAdapter = getApplicationComponents().provideSearchResultEventsAdapter();
        searchResultPresenter.attachView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_result_events, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerViewSearchResult);
        viewFlipper = rootView.findViewById(R.id.viewFlipper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(searchResultAdapter);
        searchResultPresenter.onCreate();
        return rootView;
    }

    @Override
    public void onDestroy() {
        searchResultPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showSearchResult(ArrayList<SearchResults> arrayListSearchResult) {
        viewFlipper.setDisplayedChild(VIEW_DATA);
        searchResultAdapter.setItemsSearchResults(arrayListSearchResult);
    }

    @Override
    public void clickItemSearchResult() {
        searchResultAdapter.setOnItemClickListener(item -> {
            Toast toast = Toast.makeText(getContext(), getString(R.string.selected) + item.getTitle(),
                    Toast.LENGTH_SHORT);
            toast.show();
        });
    }

    @Override
    public void showLoadingError() {
        viewFlipper.setDisplayedChild(VIEW_ERROR);
    }

    @Override
    public void showProgressView() {
        viewFlipper.setDisplayedChild(VIEW_LOADING);
    }

    @Override
    public void update() {
        searchResultAdapter.setItemsSearchResults(searchResultPresenter.initSearchResults());
    }
}
