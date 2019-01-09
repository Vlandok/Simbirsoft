package com.vlad.lesson4.presentation.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vlad.lesson4.R;
import com.vlad.lesson4.presentation.ui.base.BaseActivity;
import com.vlad.lesson4.presentation.ui.help.HelpFragment;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditFragment;
import com.vlad.lesson4.presentation.ui.search.SearchFragment;

public class MainActivity extends BaseActivity implements MainMvpView {

    private Toolbar toolbar;
    private TextView textViewToolbar;
    private Button buttonNewsBottomNav;
    private Button buttonSearchBottomNav;
    private Button buttonHelpBottomNav;
    private Button buttonHistoryBottomNav;
    private Button buttonProfileBottomNav;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    private MainPresenter mainPresenter;

    public final static int TWO = 2;
    public final static String EMPTY = " ";

    public static Intent createStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        textViewToolbar = findViewById(R.id.textViewToolbar);
        buttonNewsBottomNav = findViewById(R.id.newsBottomNav);
        buttonSearchBottomNav = findViewById(R.id.searchBottomNav);
        buttonHelpBottomNav = findViewById(R.id.helpBottomNav);
        buttonHistoryBottomNav = findViewById(R.id.historyBottomNav);
        buttonProfileBottomNav = findViewById(R.id.profileBottomNav);
        toolbar.setTitle(EMPTY);
        setSupportActionBar(toolbar);
        mainPresenter = getApplicationComponents().provideMainPresenter();
        mainPresenter.attachView(this);
        fragmentManager = getSupportFragmentManager();
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
        buttonSearchBottomNav.setOnClickListener(__ ->
                replaceFragment(SearchFragment.getInstance(), buttonSearchBottomNav, null,
                        R.id.constraintLayoutToolbarSearch, R.id.textViewToolbar));

        buttonHelpBottomNav.setOnClickListener(__ ->
                replaceFragment(HelpFragment.getInstance(), buttonHelpBottomNav,
                        getResources().getDrawable(R.drawable.ic_back),
                        R.id.textViewToolbar, R.id.constraintLayoutToolbarSearch));

        buttonProfileBottomNav.setOnClickListener(__ ->
                replaceFragment(ProfileEditFragment.getInstance(), buttonProfileBottomNav, null,
                        R.id.textViewToolbar, R.id.constraintLayoutToolbarSearch));
        buttonHelpBottomNav.performClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        MenuItem editProfile = menu.findItem(R.id.edit_profile);
        editProfile.setVisible(false);
        return true;
    }

    private void replaceFragment(Fragment fragmentReplace, Button button, @Nullable Drawable icon,
                                 int idViewVisible, int idViewGone) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragments, fragmentReplace);
        setTextInTextViewToolbar(button.getText().toString());
        fragmentTransaction.commit();
        toolbar.setNavigationIcon(icon);
        findViewById(idViewVisible).setVisibility(View.VISIBLE);
        findViewById(idViewGone).setVisibility(View.GONE);
    }
}

