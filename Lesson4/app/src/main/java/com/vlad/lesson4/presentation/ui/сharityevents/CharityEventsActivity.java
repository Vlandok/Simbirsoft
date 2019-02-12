package com.vlad.lesson4.presentation.ui.сharityevents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BaseActivity;
import com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailActivity;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailActivity.DEFAULT_VALUE;
import static com.vlad.lesson4.presentation.ui.main.MainActivity.EMPTY;

public class CharityEventsActivity extends BaseActivity implements CharityEventsMvpView {

    public static final String ARGUMENT_ID_CATEGORY_HELP = "ARGUMENT_ID_CATEGORY_HELP";
    public static final String ARGUMENT_TITLE_CATEGORY_HELP = "ARGUMENT_TITLE_CATEGORY_HELP";

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    private int idCategory;
    private String titleToolbar;
    private CharityEventsPresenter charityEventsPresenter;
    private CharityEventsAdapter charityEventsAdapter;
    private RecyclerView recyclerView;
    private ViewFlipper viewFlipper;
    private TextView textViewTitleToolbar;
    private Toolbar toolbar;

    public static Intent createStartIntent(Context context, int idCategory, String titleToolbar) {
        Intent intent = new Intent(context, CharityEventsActivity.class);
        intent.putExtra(ARGUMENT_ID_CATEGORY_HELP, idCategory);
        intent.putExtra(ARGUMENT_TITLE_CATEGORY_HELP, titleToolbar);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_events);
        toolbar = findViewById(R.id.toolbar);
        viewFlipper = findViewById(R.id.viewFlipperCharityEvents);
        textViewTitleToolbar = findViewById(R.id.textViewToolbar);
        recyclerView = findViewById(R.id.recyclerCharityEvents);
        toolbar.setTitle(EMPTY);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        charityEventsPresenter = getApplicationComponents().provideCharityEventsPresenter();
        charityEventsAdapter = getApplicationComponents().provideCharityEventsAdapter();
        charityEventsPresenter.attachView(this);
        idCategory = getIntent().getIntExtra(ARGUMENT_ID_CATEGORY_HELP, DEFAULT_VALUE);
        titleToolbar = getIntent().getStringExtra(ARGUMENT_TITLE_CATEGORY_HELP);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(charityEventsAdapter);
        charityEventsPresenter.onCreate(idCategory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        menu.findItem(R.id.event_filter).setVisible(true);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClickEvent() {
        charityEventsAdapter.setOnEventClickListener(event ->
                startActivity(CharityEventDetailActivity.createStartIntent(this, event.getId())));
    }

    @Override
    public void onClickErrorButton() {
        charityEventsPresenter.onCreate(idCategory);
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

    @Override
    public void setTitleToolbar() {
        if (textViewTitleToolbar != null) {
            textViewTitleToolbar.setText(titleToolbar);
        }
    }
}
