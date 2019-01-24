package com.vlad.lesson4.presentation.ui.search;

import android.content.Context;
import android.os.Parcelable;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.SearchEvent;
import com.vlad.lesson4.presentation.ui.searchresultevents.SearchResultEventsFragment;
import com.vlad.lesson4.presentation.ui.searchresultnko.SearchResultNkoFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    public static final String ALL_CHARACTERS = "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";

    private final SearchResultNkoFragment searchResultNkoFragment = SearchResultNkoFragment.getInstance();
    private final SearchResultEventsFragment searchResultEventsFragment = SearchResultEventsFragment.getInstance();

    private ArrayList<SearchEvent> events;

    public FragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        events = new ArrayList<>();
        events.add(new SearchEvent(context.getString(R.string.on_events)));
        events.add(new SearchEvent(context.getString(R.string.on_nko)));
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return searchResultEventsFragment;
            case 1:
                return searchResultNkoFragment;
        }
        return null;
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
