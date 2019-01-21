package com.vlad.lesson4.presentation.ui.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;
import com.vlad.lesson4.presentation.ui.main.MainActivity;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsFragment;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HelpFragment extends BaseFragment implements HelpMvpView {

    public final static String FRAGMENT_TAG_HELP = "fragment_tag_help";

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    private RecyclerView recyclerView;
    private HelpAdapter helpAdapter;
    private HelpPresenter helpPresenter;
    private ViewFlipper viewFlipper;
    private MenuItem menuItem;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        BottomNavigationViewEx bottomNavigationView = Objects.requireNonNull(getActivity())
                .findViewById(R.id.bottomNavigationMenu);
        menuItem = bottomNavigationView.getMenu().findItem(R.id.i_help);
        recyclerView = rootView.findViewById(R.id.recyclerViewChooseCategoryHelp);
        viewFlipper = rootView.findViewById(R.id.viewFlipper);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), MainActivity.SPAN_COUNT));
        recyclerView.setAdapter(helpAdapter);
        helpPresenter.onCreate(getContext());
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
        helpAdapter.setOnItemClickListener(item -> {
            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity())
                    .getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerFragments,
                    CharityEventsFragment.getInstance(item.getId()));
            fragmentTransaction.commit();
        });
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
