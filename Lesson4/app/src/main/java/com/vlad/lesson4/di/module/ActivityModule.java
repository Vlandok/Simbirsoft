package com.vlad.lesson4.di.module;

import android.content.Context;

import com.vlad.lesson4.di.ActivityContext;
import com.vlad.lesson4.domain.provider.CategoryProvider;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailPresenter;
import com.vlad.lesson4.presentation.ui.help.HelpPresenter;
import com.vlad.lesson4.presentation.ui.news.NewsPresenter;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsPresenter;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;

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
    CharityEventDetailPresenter provideCharityEventDetailPresenter(EventProvider eventProvider,
                                                                   ItemsJsonProvider itemsJsonProvider) {
        return new CharityEventDetailPresenter(eventProvider, itemsJsonProvider);
    }

    @Provides
    HelpPresenter provideHelpPresenter(CategoryProvider categoryProvider,
                                       ItemsJsonProvider itemsJsonProvider) {
        return new HelpPresenter(categoryProvider, itemsJsonProvider);
    }
}
