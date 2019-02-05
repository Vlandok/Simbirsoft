package com.vlad.lesson4.data.model.db.repository;

import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.model.EventCategories;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class CategoryRepository {

    private static CategoryRepository INSTANCE = null;

    private CategoryRepository() {
    }

    public static synchronized CategoryRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRepository();
        }
        return (INSTANCE);
    }

    public EventCategories getEventCategoriesFromRealm() {
        RealmList<Category> categoryRealmList = new RealmList<>();
        EventCategories categories = new EventCategories();
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<Category> category = realm.where(Category.class).findAll();
            List<Category> categoryCopy = realm.copyFromRealm(category);
            if (categoryCopy != null) {
                categoryRealmList.addAll(categoryCopy.subList(0, categoryCopy.size()));
                categories.setCategories(categoryRealmList);
            }
        }
        return categories;
    }
}
