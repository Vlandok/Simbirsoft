package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.remote.api.FirebaseApi;

import java.util.List;

import io.reactivex.Single;

public class CategoryProvider extends BaseProvider {
    public CategoryProvider(FirebaseApi firebaseApi) {
        super(firebaseApi);
    }

    public Single<List<Category>> getCategories() {
        return firebaseApi.getCategories();
    }
}
