package com.vlad.lesson4.presentation.ui.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.di.component.ActivityComponent;
import com.vlad.lesson4.domain.provider.CategoryProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BaseFragmentMoxy;
import com.vlad.lesson4.presentation.ui.main.MainActivity;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsActivity;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HelpFragment extends BaseFragmentMoxy implements HelpMvpView {

    public final static String FRAGMENT_TAG_HELP = "FRAGMENT_TAG_HELP";

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    @Inject
    HelpAdapter helpAdapter;

    @Inject
    @InjectPresenter
    HelpPresenter helpPresenter;

    private ViewFlipper viewFlipper;
    private Button buttonError;
    private RecyclerView recyclerView;
    private MenuItem menuItem;
    private TextView textViewTitleToolbar;
    private ActivityComponent activityComponent;

    @Inject
    CategoryProvider categoryProvider;
    @Inject
    ItemsJsonProvider itemsJsonProvider;

    @ProvidePresenter
    HelpPresenter providePresenter() {
        return helpPresenter;
    }

    public HelpFragment() {

    }

    public static HelpFragment getInstance() {
        return new HelpFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = getActivityComponent();
        activityComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        viewFlipper = rootView.findViewById(R.id.viewFlipperHelp);
        buttonError = rootView.findViewById(R.id.buttonRetryHelp);
        recyclerView = rootView.findViewById(R.id.recyclerViewChooseCategoryHelp);
        BottomNavigationViewEx bottomNavigationView = Objects.requireNonNull(getActivity())
                .findViewById(R.id.bottomNavigationMenu);
        textViewTitleToolbar = Objects.requireNonNull(getActivity())
                .findViewById(R.id.textViewToolbar);
        menuItem = bottomNavigationView.getMenu().findItem(R.id.i_help);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), MainActivity.SPAN_COUNT));
        recyclerView.setAdapter(helpAdapter);
        helpPresenter.attachView(this);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        textViewTitleToolbar.setText(R.string.title_help_categ);
        menuItem.setEnabled(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        menuItem.setEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        providePresenter().detachView(this);
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
            buttonError.setOnClickListener(view -> helpPresenter.getItemsCategory());
        }
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
