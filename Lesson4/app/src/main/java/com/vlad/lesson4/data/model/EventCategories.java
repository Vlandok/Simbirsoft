
package com.vlad.lesson4.data.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class EventCategories {

    @SerializedName("categories")
    @Expose
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public static List<Category> getCategoriesFromJson(EventCategories eventCategories, String data) {
        Type type = new TypeToken<EventCategories>() {
        }.getType();
        try {
            eventCategories = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return eventCategories != null ? eventCategories.getCategories() : null;
    }
}
