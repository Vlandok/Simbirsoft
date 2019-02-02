
package com.vlad.lesson4.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class EventCategories extends RealmObject {

    @SerializedName("categories")
    @Expose
    private RealmList<Category> categories;

    public RealmList<Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<Category> categories) {
        this.categories = categories;
    }
}
