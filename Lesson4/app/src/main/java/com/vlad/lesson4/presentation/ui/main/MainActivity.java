package com.vlad.lesson4.presentation.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.vlad.lesson4.R;
import com.vlad.lesson4.presentation.ui.authorization.AuthorizationActivity;
import com.vlad.lesson4.presentation.ui.base.BaseActivity;
import com.vlad.lesson4.presentation.ui.help.HelpFragment;
import com.vlad.lesson4.presentation.ui.news.NewsFragment;
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
    private Fragment fragment = null;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private MainPresenter mainPresenter;

    private final int WIDTH_HEIGHT_ICON = 40;
    private final int TEXT_SIZE_BOT_MENU = 11;
    private final int MARGIN_TOP_ICON_BOT_MENU = -8;

    public final static int BOTTOM_NAVIGATION_MENU_VISIBILITY = 1;
    public final static int SPAN_COUNT = 2;
    public final static String EMPTY = " ";
    public final static String NOTHING = "";
    public final static String DOT = ".";

    public static Intent createStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        mAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationMenu);
        textViewToolbar = findViewById(R.id.textViewToolbar);
        setParametersBottomNav();
        toolbar.setTitle(EMPTY);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.containerFragments, HelpFragment.getInstance(),
                    HelpFragment.FRAGMENT_TAG_HELP);
            fragmentTransaction.add(R.id.containerFragments, SearchFragment.getInstance(),
                    SearchFragment.FRAGMENT_TAG_SEARCH);
            fragmentTransaction.add(R.id.containerFragments, ProfileEditFragment.getInstance(),
                    ProfileEditFragment.FRAGMENT_TAG_PROFILE);
            fragmentTransaction.add(R.id.containerFragments, NewsFragment.getInstance(),
                    NewsFragment.FRAGMENT_TAG_NEWS);
            fragmentTransaction.commit();
        }
        setSupportActionBar(toolbar);
        mainPresenter = getApplicationComponents().provideMainPresenter();
        mainPresenter.attachView(this);
        toolbar.setNavigationOnClickListener(view -> finish());
        mainPresenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
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
    public void clickButtonBottomNav(Bundle savedInstanceState) {
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            menuItem.setEnabled(false);
            if (id == R.id.i_profile && currentUser == null) {
                menuItem.setEnabled(true);
                menuItem.setCheckable(false);
                menuItem.setChecked(false);
                startActivity(AuthorizationActivity.createStartIntent(this));
            } else {
                fragmentTransaction = fragmentManager.beginTransaction();
                switch (id) {
                    case R.id.i_news: {
                        fragment = fragmentManager.findFragmentByTag(NewsFragment.FRAGMENT_TAG_NEWS);
                        if (fragment != null) {
                            fragmentTransaction.replace(R.id.containerFragments, fragment);
                        } else {
                            fragmentTransaction.replace(R.id.containerFragments, NewsFragment.getInstance());
                        }
                        findViewById(R.id.textViewToolbar).setVisibility(View.VISIBLE);
                        findViewById(R.id.constraintLayoutToolbarSearch).setVisibility(View.GONE);
                        setTextInTextViewToolbar(menuItem.getTitle().toString());
                        break;
                    }
                    case R.id.i_search: {
                        fragment = fragmentManager.findFragmentByTag(SearchFragment.FRAGMENT_TAG_SEARCH);
                        if (fragment != null) {
                            fragmentTransaction.replace(R.id.containerFragments, fragment);
                        } else {
                            fragmentTransaction.replace(R.id.containerFragments, SearchFragment.getInstance());
                        }
                        findViewById(R.id.constraintLayoutToolbarSearch).setVisibility(View.VISIBLE);
                        findViewById(R.id.textViewToolbar).setVisibility(View.GONE);
                        setTextInTextViewToolbar(menuItem.getTitle().toString());
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
                        setTextInTextViewToolbar(menuItem.getTitle().toString());
                        break;
                    }
                    case R.id.i_profile: {
                        menuItem.setCheckable(true);
                        fragment = fragmentManager.findFragmentByTag(ProfileEditFragment.FRAGMENT_TAG_PROFILE);
                        if (fragment != null) {
                            fragmentTransaction.replace(R.id.containerFragments, fragment);
                        } else {
                            fragmentTransaction.replace(R.id.containerFragments, ProfileEditFragment.getInstance());
                        }
                        findViewById(R.id.textViewToolbar).setVisibility(View.VISIBLE);
                        findViewById(R.id.constraintLayoutToolbarSearch).setVisibility(View.GONE);
                        setTextInTextViewToolbar(menuItem.getTitle().toString());
                        break;
                    }
                }
                fragmentTransaction.commit();
            }
            return true;
        });
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.i_help);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private void setParametersBottomNav() {
        bottomNavigationView.setIconSize(WIDTH_HEIGHT_ICON, WIDTH_HEIGHT_ICON);
        bottomNavigationView.setTextSize(TEXT_SIZE_BOT_MENU);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.setLabelVisibilityMode(BOTTOM_NAVIGATION_MENU_VISIBILITY);
        bottomNavigationView.setItemHorizontalTranslationEnabled(false);
        bottomNavigationView.setIconsMarginTop(MARGIN_TOP_ICON_BOT_MENU);
    }
}

