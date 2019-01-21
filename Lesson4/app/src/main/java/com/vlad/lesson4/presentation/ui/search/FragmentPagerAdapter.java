package com.vlad.lesson4.presentation.ui.search;

import android.os.Parcelable;

import com.vlad.lesson4.data.model.SearchEvent;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsFragment;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    public static final String ON_EVENTS = "По мероприятиям";
    public static final String ON_NKO = "По НКО";
    public static final String ALL_CHARACTERS = "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";

    private ArrayList<SearchEvent> events;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        events = new ArrayList<>();
        events.add(new SearchEvent(ON_EVENTS));
        events.add(new SearchEvent(ON_NKO));
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
