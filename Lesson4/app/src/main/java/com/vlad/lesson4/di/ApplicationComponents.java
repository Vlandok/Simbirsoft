package com.vlad.lesson4.di;

import android.content.Context;

import com.vlad.lesson4.data.remote.ApiService;
import com.vlad.lesson4.data.remote.api.FirebaseApi;
import com.vlad.lesson4.domain.provider.CategoryProvider;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailPresenter;
import com.vlad.lesson4.presentation.ui.help.HelpAdapter;
import com.vlad.lesson4.presentation.ui.help.HelpPresenter;
import com.vlad.lesson4.presentation.ui.main.MainPresenter;
import com.vlad.lesson4.presentation.ui.news.NewsPresenter;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditAdapter;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditPresenter;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsAdapter;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsPresenter;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoAdapter;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoPresenter;
import com.vlad.lesson4.presentation.ui.splashscreen.SplashScreenPresenter;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsAdapter;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsPresenter;

public class ApplicationComponents {

    private static ApplicationComponents instance;

    private Context context;
    private FirebaseApi firebaseApi;

    private final CategoryProvider categoryProvider;
    private final EventProvider eventProvider;
    private final ItemsJsonProvider itemsJsonProvider;

    private ApplicationComponents(Context context) {
        this.context = context;
        this.firebaseApi = ApiService.Creator.newApiService(context);
        this.categoryProvider = new CategoryProvider(firebaseApi);
        this.eventProvider = new EventProvider(firebaseApi);
        this.itemsJsonProvider = new ItemsJsonProvider(context);
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

    public CategoryProvider provideCategoryProvider() {
        return categoryProvider;
    }

    public ItemsJsonProvider provideItemsJsonProvider() {
        return itemsJsonProvider;
    }

    public EventProvider provideEventProvider() {
        return eventProvider;
    }

    public HelpPresenter provideHelpPresenter() {
        return new HelpPresenter(provideCategoryProvider(), provideItemsJsonProvider());
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

    public NewsPresenter provideNewsPresenter() {
        return new NewsPresenter(provideEventProvider(), provideItemsJsonProvider());
    }

    public CharityEventsPresenter provideCharityEventsPresenter() {
        return new CharityEventsPresenter(provideEventProvider(), provideItemsJsonProvider());
    }

    public CharityEventsAdapter provideCharityEventsAdapter() {
        return new CharityEventsAdapter();
    }

    public CharityEventDetailPresenter provideCharityEventDetailPresenter() {
        return new CharityEventDetailPresenter(provideEventProvider(), provideItemsJsonProvider());
    }

}
