package com.vlad.lesson4.presentation.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.vlad.lesson4.R;
import com.vlad.lesson4.presentation.ui.base.BaseActivity;
import com.vlad.lesson4.presentation.ui.help.HelpFragment;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditFragment;
import com.vlad.lesson4.presentation.ui.search.SearchFragment;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseActivity implements MainMvpView {

    private Toolbar toolbar;
    private BottomNavigationViewEx bottomNavigationView;
    private TextView textViewToolbar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;

    private MainPresenter mainPresenter;

    private final int WIDTH_HEIGHT_ICON = 40;
    private final int TEXT_SIZE_BOT_MENU = 11;
    private final int MARGIN_TOP_ICON_BOT_MENU = -8;

    public final static int ONE = 1;
    public final static int SPAN_COUNT = 2;
    public final static String EMPTY = " ";
    public final static String NOTHING = "";
    public final static String DOT = ".";
    public final static String CLOSE_BRACKET = ")";
    public final static String OPEN_BRACKET = "(";

    public static Intent createStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationMenu);
        textViewToolbar = findViewById(R.id.textViewToolbar);
        setParametersBottomNav();
        toolbar.setTitle(EMPTY);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerFragments, HelpFragment.getInstance(), HelpFragment.FRAGMENT_TAG_HELP);
        fragmentTransaction.add(R.id.containerFragments, SearchFragment.getInstance(),
                SearchFragment.FRAGMENT_TAG_SEARCH);
        fragmentTransaction.add(R.id.containerFragments, ProfileEditFragment.getInstance(),
                ProfileEditFragment.FRAGMENT_TAG_PROFILE);
        fragmentTransaction.commit();
        setSupportActionBar(toolbar);
        mainPresenter = getApplicationComponents().provideMainPresenter();
        mainPresenter.attachView(this);
        toolbar.setNavigationOnClickListener(view -> finish());
        mainPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void setTextInTextViewToolbar(String titleToolbar) {
        textViewToolbar.setText(titleToolbar);
    }

    @Override
    public void clickButtonBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            setTextInTextViewToolbar(menuItem.getTitle().toString());
            fragmentTransaction = fragmentManager.beginTransaction();
            switch (id) {
                case R.id.i_search: {
                    fragment = fragmentManager.findFragmentByTag(SearchFragment.FRAGMENT_TAG_SEARCH);
                    if (fragment != null) {
                        fragmentTransaction.replace(R.id.containerFragments, fragment);
                    } else {
                        fragmentTransaction.replace(R.id.containerFragments, SearchFragment.getInstance());
                    }
                    findViewById(R.id.constraintLayoutToolbarSearch).setVisibility(View.VISIBLE);
                    findViewById(R.id.textViewToolbar).setVisibility(View.GONE);
                    break;
                }
                case R.id.i_help: {
                    fragment = fragmentManager.findFragmentByTag(HelpFragment.FRAGMENT_TAG_HELP);
                    if (fragment != null) {
                        fragmentTransaction.replace(R.id.containerFragments, fragment);
                    } else {
                        fragmentTransaction.replace(R.id.containerFragments, HelpFragment.getInstance());
                    }
                    findViewById(R.id.textViewToolbar).setVisibility(View.VISIBLE);
                    findViewById(R.id.constraintLayoutToolbarSearch).setVisibility(View.GONE);
                    break;
                }
                case R.id.i_profile: {
                    fragment = fragmentManager.findFragmentByTag(ProfileEditFragment.FRAGMENT_TAG_PROFILE);
                    if (fragment != null) {
                        fragmentTransaction.replace(R.id.containerFragments, fragment);
                    } else {
                        fragmentTransaction.replace(R.id.containerFragments, ProfileEditFragment.getInstance());
                    }
                    findViewById(R.id.textViewToolbar).setVisibility(View.VISIBLE);
                    findViewById(R.id.constraintLayoutToolbarSearch).setVisibility(View.GONE);
                    break;
                }
            }
            menuItem.setEnabled(false);
            fragmentTransaction.commit();
            return true;
        });
        bottomNavigationView.setSelectedItemId(R.id.i_help);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem editProfile = menu.findItem(R.id.edit_profile);
        MenuItem eventFilter = menu.findItem(R.id.event_filter);
        MenuItem eventShare = menu.findItem(R.id.share_event);
        eventShare.setVisible(false);
        eventFilter.setVisible(false);
        editProfile.setVisible(false);
        return true;
    }

    private void setParametersBottomNav() {
        bottomNavigationView.setIconSize(WIDTH_HEIGHT_ICON, WIDTH_HEIGHT_ICON);
        bottomNavigationView.setTextSize(TEXT_SIZE_BOT_MENU);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.setLabelVisibilityMode(ONE);
        bottomNavigationView.setItemHorizontalTranslationEnabled(false);
        bottomNavigationView.setIconsMarginTop(MARGIN_TOP_ICON_BOT_MENU);
    }
}

