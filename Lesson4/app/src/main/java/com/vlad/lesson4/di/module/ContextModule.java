package com.vlad.lesson4.di.module;

import android.content.Context;

import com.vlad.lesson4.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @Singleton
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
