package com.vlad.lesson4.di.module;

import android.content.Context;

import com.vlad.lesson4.di.ActivityContext;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemsJsonModule {

    @Provides
    ItemsJsonProvider itemsJsonProvider(@ActivityContext Context context) {
        return new ItemsJsonProvider(context);
    }
}
