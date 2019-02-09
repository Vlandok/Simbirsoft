package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.remote.ApiService;

import java.util.List;

import io.reactivex.Single;

public class CategoryProvider {

    private final ApiService apiService;

    public CategoryProvider(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<List<Category>> getCategories() {
        return apiService.getCategories();
    }
}
