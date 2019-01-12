package com.vlad.lesson4.presentation.ui.search;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsFragment;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoFragment;

import java.util.ArrayList;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    public static final String ON_EVENTS = "По мероприятиям";
    public static final String ON_NKO = "По НКО";
    public static final String ALL_CHARACTERS = "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";

    private ArrayList<Event> events;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        events = new ArrayList<>();
        events.add(new Event(ON_EVENTS));
        events.add(new Event(ON_NKO));
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SearchResultEventsFragment.getInstance();
            case 1:
                return SearchResultNkoFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return events.get(position).getTitle();
    }
}
