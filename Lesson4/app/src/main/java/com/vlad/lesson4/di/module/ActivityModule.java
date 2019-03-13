package com.vlad.lesson4.di.module;

import android.content.Context;

import com.vlad.lesson4.data.remote.api.FirebaseApi;
import com.vlad.lesson4.di.ActivityContext;
import com.vlad.lesson4.di.scope.ActivityScope;
import com.vlad.lesson4.domain.provider.CategoryProvider;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.domain.provider.ProfileEditProvider;
import com.vlad.lesson4.domain.provider.UserProvider;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return appCompatActivity;
    }

    @ActivityScope
    @Provides
    EventProvider provideEventProvider(FirebaseApi firebaseApi) {
        return new EventProvider(firebaseApi);
    }

    @ActivityScope
    @Provides
    CategoryProvider provideCategoryProvider(FirebaseApi firebaseApi) {
        return new CategoryProvider(firebaseApi);
    }

    @ActivityScope
    @Provides
    ProfileEditProvider provideProfileEditProvider(FirebaseApi firebaseApi) {
        return new ProfileEditProvider(firebaseApi);
    }

    @ActivityScope
    @Provides
    UserProvider userProvider(FirebaseApi firebaseApi) {
        return new UserProvider(firebaseApi);
    }

    @ActivityScope
    @Provides
    ItemsJsonProvider itemsJsonProvider(FirebaseApi firebaseApi, @ActivityContext Context context) {
        return new ItemsJsonProvider(firebaseApi, context);
    }
}
