package com.vlad.lesson4.di;

import android.content.Context;

import com.vlad.lesson4.presentation.ui.help.HelpAdapter;
import com.vlad.lesson4.presentation.ui.help.HelpPresenter;
import com.vlad.lesson4.presentation.ui.main.MainPresenter;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditPresenter;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsAdapter;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsPresenter;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoAdapter;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoPresenter;
import com.vlad.lesson4.presentation.ui.splashscreen.SplashScreenPresenter;

public class ApplicationComponents {

    private static volatile ApplicationComponents instance;

    private Context context;


    private ApplicationComponents(Context context) {
        this.context = context;
    }

    public static ApplicationComponents getInstance(Context context) {
        ApplicationComponents localInstance = instance;
        if (localInstance == null) {
            synchronized (ApplicationComponents.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ApplicationComponents(context);
                }
            }
        }
        return localInstance;
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
