package com.vlad.lesson4.di.module;

import com.vlad.lesson4.data.remote.api.FirebaseApi;
import com.vlad.lesson4.domain.provider.CategoryProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryModule {


    @Provides
    CategoryProvider categoryProvider(FirebaseApi firebaseApi) {
        return new CategoryProvider(firebaseApi);
    }
}
