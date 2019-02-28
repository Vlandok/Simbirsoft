package com.vlad.lesson4.di.module;

import android.content.Context;

import com.vlad.lesson4.di.ActivityContext;
import com.vlad.lesson4.domain.provider.CategoryProvider;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailPresenter;
import com.vlad.lesson4.presentation.ui.help.HelpAdapter;
import com.vlad.lesson4.presentation.ui.help.HelpPresenter;
import com.vlad.lesson4.presentation.ui.main.MainPresenter;
import com.vlad.lesson4.presentation.ui.news.NewsPresenter;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditAdapter;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsAdapter;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsPresenter;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoAdapter;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoPresenter;
import com.vlad.lesson4.presentation.ui.splashscreen.SplashScreenPresenter;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsAdapter;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsPresenter;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module(includes = {CategoryModule.class, UserModule.class, ItemsJsonModule.class, EventModule.class})
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @ActivityContext
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SplashScreenPresenter provideSplashScreenPresenter() {
        return new SplashScreenPresenter();
    }

    @Provides
    SearchResultNkoPresenter provideSearchResultNkoPresenter() {
        return new SearchResultNkoPresenter();
    }

    @Provides
    SearchResultNkoAdapter provideSearchResultNkoAdapter() {
        return new SearchResultNkoAdapter();
    }

    @Provides
    SearchResultEventsPresenter provideSearchResultEventsPresenter() {
        return new SearchResultEventsPresenter();
    }

    @Provides
    SearchResultEventsAdapter provideSearchResultEventsAdapter() {
        return new SearchResultEventsAdapter();
    }

    @Provides
    NewsPresenter provideNewsPresenter(EventProvider eventProvider,
                                       ItemsJsonProvider itemsJsonProvider) {
        return new NewsPresenter(eventProvider, itemsJsonProvider);
    }

    @Provides
    CharityEventsPresenter provideCharityEventsPresenter(EventProvider eventProvider,
                                                         ItemsJsonProvider itemsJsonProvider) {
        return new CharityEventsPresenter(eventProvider, itemsJsonProvider);
    }

    @Provides
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    ProfileEditAdapter provideProfileEditAdapter() {
        return new ProfileEditAdapter();
    }

    @Provides
    CharityEventsAdapter provideCharityEventsAdapter() {
        return new CharityEventsAdapter();
    }

    @Provides
    CharityEventDetailPresenter provideCharityEventDetailPresenter(EventProvider eventProvider,
                                                                   ItemsJsonProvider itemsJsonProvider) {
        return new CharityEventDetailPresenter(eventProvider, itemsJsonProvider);
    }

    @Provides
    HelpPresenter provideHelpPresenter(CategoryProvider categoryProvider,
                                       ItemsJsonProvider itemsJsonProvider) {
        return new HelpPresenter(categoryProvider, itemsJsonProvider);
    }

    @Provides
    HelpAdapter provideHelpAdapter() {
        return new HelpAdapter();
    }
}
