package com.vlad.lesson4.presentation.ui.help;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.ItemForChooseCategoryHelp;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;
import com.vlad.lesson4.presentation.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.Objects;


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
            Toast toast = Toast.makeText(getContext(), getString(R.string.choose_category) + item.getNameCategory(),
                    Toast.LENGTH_SHORT);
            toast.show();
        });
    }

    @Override
    public void showItemsCategory(ArrayList<ItemForChooseCategoryHelp> arrayListHelpCategory) {
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
