
package com.vlad.lesson4.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventCategories implements Parcelable {

    @SerializedName("categories")
    @Expose
    private List<Category> categories;

    public EventCategories(List<Category> categories) {
        this.categories = categories;
    }

    private EventCategories(Parcel parcel) {
        categories = new ArrayList<>();
        parcel.readList(categories, Category.class.getClassLoader());
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(categories);
    }


    public static final Parcelable.Creator<EventCategories> CREATOR =
            new Parcelable.Creator<EventCategories>() {

        @Override
        public EventCategories createFromParcel(Parcel in) {
            return new EventCategories(in);
        }

        @Override
        public EventCategories[] newArray(int size) {
            return new EventCategories[size];
        }
    };
}
