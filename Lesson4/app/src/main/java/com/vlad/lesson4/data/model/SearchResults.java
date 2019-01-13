package com.vlad.lesson4.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchResults implements Parcelable {
    private String title;

    public SearchResults(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
    }

    public static final Parcelable.Creator<SearchResults> CREATOR = new Parcelable.Creator<SearchResults>() {
        // распаковываем объект из Parcel
        public SearchResults createFromParcel(Parcel in) {
            return new SearchResults(in);
        }

        public SearchResults[] newArray(int size) {
            return new SearchResults[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private SearchResults(Parcel parcel) {
        title = parcel.readString();
    }
}
