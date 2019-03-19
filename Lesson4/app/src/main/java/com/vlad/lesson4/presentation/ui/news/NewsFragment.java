package com.vlad.lesson4.presentation.ui.news;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vlad.lesson4.MyApplication;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;
import com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailActivity;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsAdapter;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsFragment extends BaseFragment implements NewsMvpView {

    public final static String FRAGMENT_TAG_NEWS = "FRAGMENT_TAG_NEWS";

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    @Inject
    @InjectPresenter
    NewsPresenter newsPresenter;
    private CharityEventsAdapter charityEventsAdapter;
    private MenuItem menuItem;
    private RecyclerView recyclerView;
    private ViewFlipper viewFlipper;
    private Button buttonError;
    private TextView textViewTitleToolbar;

    @ProvidePresenter
    NewsPresenter provideNewsPresenter() {
        return newsPresenter;
    }

    public NewsFragment() {

    }

    public static NewsFragment getInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MyApplication.getInstance()
                .plusActivityComponent((AppCompatActivity) getActivity()).inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        BottomNavigationViewEx bottomNavigationView = Objects.requireNonNull(getActivity())
                .findViewById(R.id.bottomNavigationMenu);
        textViewTitleToolbar = Objects.requireNonNull(getActivity())
                .findViewById(R.id.textViewToolbar);
        buttonError = rootView.findViewById(R.id.buttonRetryHelp);
        menuItem = bottomNavigationView.getMenu().findItem(R.id.i_news);
        viewFlipper = rootView.findViewById(R.id.viewFlipperCharityEvents);
        recyclerView = rootView.findViewById(R.id.recyclerCharityEvents);
        charityEventsAdapter = new CharityEventsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(charityEventsAdapter);
        return rootView;
    }

    @Override
    public void onStart() {
        textViewTitleToolbar.setText(R.string.news_bottom_nav);
        menuItem.setEnabled(false);
        super.onStart();
    }

    @Override
    public void onPause() {
        menuItem.setEnabled(true);
        super.onPause();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.event_filter).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClickEvent() {
        charityEventsAdapter.setOnEventClickListener(event ->
                startActivity(CharityEventDetailActivity.createStartIntent(getActivity(), event.getId())));
    }

    @Override
    public void onClickErrorButton() {
        if (newsPresenter != null && buttonError != null) {
            buttonError.setOnClickListener(view -> newsPresenter.getCharityEvents());
        }
    }

    @Override
    public void showCharityEvents(List<Event> arrayListEvent) {
        viewFlipper.setDisplayedChild(VIEW_DATA);
        charityEventsAdapter.setArrayListCharityEvents(arrayListEvent);
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
