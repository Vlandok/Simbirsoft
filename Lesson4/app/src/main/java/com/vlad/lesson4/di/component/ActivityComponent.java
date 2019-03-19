package com.vlad.lesson4.di.component;

import com.vlad.lesson4.di.module.ActivityModule;
import com.vlad.lesson4.di.scope.ActivityScope;
import com.vlad.lesson4.presentation.ui.authorization.AuthorizationActivity;
import com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailActivity;
import com.vlad.lesson4.presentation.ui.help.HelpFragment;
import com.vlad.lesson4.presentation.ui.main.MainActivity;
import com.vlad.lesson4.presentation.ui.news.NewsFragment;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditFragment;
import com.vlad.lesson4.presentation.ui.search.SearchFragment;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsFragment;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoFragment;
import com.vlad.lesson4.presentation.ui.splashscreen.SplashScreenActivity;
import com.vlad.lesson4.presentation.ui.сharityevents.CharityEventsActivity;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
@ActivityScope
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(AuthorizationActivity activity);

    void inject(CharityEventsActivity activity);

    void inject(HelpFragment fragment);

    void inject(NewsFragment fragment);

    void inject(ProfileEditFragment fragment);

    void inject(SearchFragment fragment);

    void inject(SearchResultNkoFragment fragment);

    void inject(SearchResultEventsFragment fragment);

    void inject(SplashScreenActivity activity);

    void inject(CharityEventDetailActivity activity);
}
