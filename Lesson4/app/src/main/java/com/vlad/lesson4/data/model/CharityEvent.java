
package com.vlad.lesson4.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CharityEvent implements Parcelable {

    @SerializedName("events")
    @Expose
    private List<Event> events = null;

    public CharityEvent(List<Event> events) {
        this.events = events;
    }

    private CharityEvent(Parcel in) {
        events = new ArrayList<>();
        in.readList(events, ClassLoader.getSystemClassLoader());
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(events);
    }

    public static final Creator<CharityEvent> CREATOR = new Creator<CharityEvent>() {
        @Override
        public CharityEvent createFromParcel(Parcel in) {
            return new CharityEvent(in);
        }

        @Override
        public CharityEvent[] newArray(int size) {
            return new CharityEvent[size];
        }
    };
}
