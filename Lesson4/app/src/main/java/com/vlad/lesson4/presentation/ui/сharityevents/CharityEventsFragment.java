package com.vlad.lesson4.presentation.ui.сharityevents;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;
import com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailActivity;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CharityEventsFragment extends BaseFragment implements CharityEventsMvpView {

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    public static final String ARGUMENT_ID_CATEGORY_HELP = "argument_id_category_help";

    private int idCategory;
    private CharityEventsPresenter charityEventsPresenter;
    private CharityEventsAdapter charityEventsAdapter;
    private RecyclerView recyclerView;
    private ViewFlipper viewFlipper;


    public CharityEventsFragment() {
        // Required empty public constructor
    }

    public static CharityEventsFragment getInstance(int idCategory) {
        CharityEventsFragment fragment = new CharityEventsFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_ID_CATEGORY_HELP, idCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        charityEventsPresenter = getApplicationComponents().provideCharityEventsPresenter();
        charityEventsAdapter = getApplicationComponents().provideCharityEventsAdapter();
        charityEventsPresenter.attachView(this);
        if (getArguments() != null) {
            idCategory = getArguments().getInt(ARGUMENT_ID_CATEGORY_HELP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_charity_events, container, false);
        viewFlipper = rootView.findViewById(R.id.viewFlipperCharityEvents);
        recyclerView = rootView.findViewById(R.id.recyclerCharityEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(charityEventsAdapter);
        charityEventsPresenter.onCreate(Objects.requireNonNull(getActivity()).getApplicationContext());
        return rootView;
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
    public void showCharityEvents(List<Event> arrayListEvent) {
        viewFlipper.setDisplayedChild(VIEW_DATA);
        charityEventsAdapter.setArrayListCharityEvents(getEventsCategory(arrayListEvent));
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
    public List<Event> getEventsCategory(List<Event> arrayListEvent) {
        for (Iterator<Event> it = arrayListEvent.iterator(); it.hasNext(); ) {
            Event event = it.next();
            if (event.getIdCategoryHelp() != idCategory) {
                it.remove();
            }
        }
        return arrayListEvent;
    }
}
