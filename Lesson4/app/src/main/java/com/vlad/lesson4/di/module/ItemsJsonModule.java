package com.vlad.lesson4.di.module;

import android.content.Context;

import com.vlad.lesson4.di.ActivityContext;
import com.vlad.lesson4.di.scope.ActivityScope;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemsJsonModule {

    @ActivityScope
    @Provides
    ItemsJsonProvider itemsJsonProvider(@ActivityContext Context context) {
        return new ItemsJsonProvider(context);
    }
}
