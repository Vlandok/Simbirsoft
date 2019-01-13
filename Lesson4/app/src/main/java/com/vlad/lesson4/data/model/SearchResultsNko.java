package com.vlad.lesson4.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchResultsNko implements Parcelable {
    private String title;

    public SearchResultsNko(String title) {
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

    public static final Parcelable.Creator<SearchResultsNko> CREATOR = new Parcelable.Creator<SearchResultsNko>() {
        // распаковываем объект из Parcel
        public SearchResultsNko createFromParcel(Parcel in) {
            return new SearchResultsNko(in);
        }

        public SearchResultsNko[] newArray(int size) {
            return new SearchResultsNko[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private SearchResultsNko(Parcel parcel) {
        title = parcel.readString();
    }
}