package com.vlad.lesson4.presentation.ui.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.model.EventCategories;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;
import com.vlad.lesson4.presentation.ui.main.MainActivity;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsActivity;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HelpFragment extends BaseFragment implements HelpMvpView {

    public final static String FRAGMENT_TAG_HELP = "FRAGMENT_TAG_HELP";
    private static final String FILE_JSON_CATEGORIES = "categories.json";

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    private RecyclerView recyclerView;
    private HelpAdapter helpAdapter;
    private HelpPresenter helpPresenter;
    private ViewFlipper viewFlipper;
    private MenuItem menuItem;
    private Button buttonError;
    private TextView textViewTitleToolbar;

    public HelpFragment() {

    }

    public static HelpFragment getInstance() {
        return new HelpFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helpPresenter = getApplicationComponents().provideHelpPresenter();
        helpAdapter = getApplicationComponents().provideHelpAdapter();
        helpPresenter.attachView(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        BottomNavigationViewEx bottomNavigationView = Objects.requireNonNull(getActivity())
                .findViewById(R.id.bottomNavigationMenu);
        textViewTitleToolbar = Objects.requireNonNull(getActivity())
                .findViewById(R.id.textViewToolbar);
        buttonError = rootView.findViewById(R.id.buttonRetryHelp);
        menuItem = bottomNavigationView.getMenu().findItem(R.id.i_help);
        recyclerView = rootView.findViewById(R.id.recyclerViewChooseCategoryHelp);
        viewFlipper = rootView.findViewById(R.id.viewFlipper);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), MainActivity.SPAN_COUNT));
        recyclerView.setAdapter(helpAdapter);
        if (savedInstanceState != null) {
            textViewTitleToolbar.setText(R.string.title_help_categ);
            menuItem.setEnabled(false);
        }
        helpPresenter.onCreate();
        return rootView;
    }

    @Override
    public void onPause() {
        menuItem.setEnabled(true);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        helpPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onClickCategory() {
        helpAdapter.setOnItemClickListener(item ->
                startActivity(CharityEventsActivity.createStartIntent(getContext(),
                        item.getId(), item.getName())));
    }

    @Override
    public void onClickErrorButton() {
        if (helpPresenter != null && buttonError != null) {
            buttonError.setOnClickListener(view -> helpPresenter.onCreate());
        }
    }

    @Override
    public List<Category> getListCategoriesFromJson() {
        EventCategories categories = new EventCategories();
        String data = JsonSupport.loadJSONFromAsset(getContext(), FILE_JSON_CATEGORIES);
        Type type = new TypeToken<EventCategories>() {
        }.getType();
        try {
            categories = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return categories != null ? categories.getCategories() : null;
    }

    @Override
    public void showItemsCategory(List<Category> arrayListHelpCategory) {
        viewFlipper.setDisplayedChild(VIEW_DATA);
        helpAdapter.setHelpCategory(arrayListHelpCategory);
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
