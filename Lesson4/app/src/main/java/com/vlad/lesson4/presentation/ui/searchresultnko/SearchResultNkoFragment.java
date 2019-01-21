package com.vlad.lesson4.presentation.ui.searchresultnko;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.SearchResultsNko;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultNkoFragment extends BaseFragment implements SearchResultNkoMvpView {

    public final static String FRAGMENT_TAG_SEARCH_NKO = "fragment_tag_search_nko";

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    private SearchResultNkoPresenter searchResultNkoPresenter;
    private RecyclerView recyclerView;
    private SearchResultNkoAdapter searchResultNkoAdapter;
    private ViewFlipper viewFlipper;

    public SearchResultNkoFragment() {

    }

    public static SearchResultNkoFragment getInstance() {
        return new SearchResultNkoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchResultNkoPresenter = getApplicationComponents().provideSearchResultNkoPresenter();
        searchResultNkoAdapter = getApplicationComponents().provideSearchResultNkoAdapter();
        searchResultNkoPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_result_nko, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerViewSearchResultNko);
        viewFlipper = rootView.findViewById(R.id.viewFlipper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(searchResultNkoAdapter);
        searchResultNkoPresenter.onCreate();
        return rootView;
    }

    @Override
    public void onDestroy() {
        searchResultNkoPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showSearchResultNko(ArrayList<SearchResultsNko> arrayListSearchResultNko) {
        viewFlipper.setDisplayedChild(VIEW_DATA);
        searchResultNkoAdapter.setItemsSearchResultsNko(arrayListSearchResultNko);
    }

    @Override
    public void clickItemSearchResultNko() {
        searchResultNkoAdapter.setOnItemClickListener(item -> {
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
}
