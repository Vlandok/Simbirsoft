package com.vlad.lesson4.di;

import android.content.Context;

import com.vlad.lesson4.presentation.ui.help.HelpAdapter;
import com.vlad.lesson4.presentation.ui.help.HelpPresenter;
import com.vlad.lesson4.presentation.ui.main.MainPresenter;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditAdapter;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditPresenter;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsAdapter;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsPresenter;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoAdapter;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoPresenter;
import com.vlad.lesson4.presentation.ui.splashscreen.SplashScreenPresenter;

public class ApplicationComponents {

    private static ApplicationComponents instance;

    private Context context;


    private ApplicationComponents(Context context) {
        this.context = context;
    }

    public static ApplicationComponents getInstance(Context context) {
        if (instance == null) {
            instance = new ApplicationComponents(context);
        }
        return instance;
    }

    public SplashScreenPresenter provideSplashScreenPresenter() {
        return new SplashScreenPresenter();
    }

    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    public ProfileEditPresenter provideProfileEditPresenter() {
        return new ProfileEditPresenter();
    }

    public ProfileEditAdapter provideProfileEditAdapter() {
        return new ProfileEditAdapter();
    }

    public HelpPresenter provideHelpPresenter() {
        return new HelpPresenter();
    }

    public HelpAdapter provideHelpAdapter() {
        return new HelpAdapter();
    }

    public SearchResultEventsPresenter provideSearchResultEventsPresenter() {
        return new SearchResultEventsPresenter();
    }

    public SearchResultEventsAdapter provideSearchResultEventsAdapter() {
        return new SearchResultEventsAdapter();
    }

    public SearchResultNkoPresenter provideSearchResultNkoPresenter() {
        return new SearchResultNkoPresenter();
    }

    public SearchResultNkoAdapter provideSearchResultNkoAdapter() {
        return new SearchResultNkoAdapter();
    }

}
