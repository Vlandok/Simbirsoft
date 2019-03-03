package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.remote.api.FirebaseApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

public class CategoryProvider {

    private final FirebaseApi firebaseApi;

    @Inject
    public CategoryProvider(FirebaseApi firebaseApi) {
        this.firebaseApi = firebaseApi;
    }

    public Single<List<Category>> getCategories() {
        return firebaseApi.getCategories();
    }

    public <T> SingleTransformer<T, T> applyScheduler() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io());
    }
}
