package com.vlad.lesson4.presentation.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vlad.lesson4.R;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class SearchFragment extends BaseFragment implements SearchMvpView {

    public final static String FRAGMENT_TAG_SEARCH = "FRAGMENT_TAG_SEARCH";

    private MenuItem menuItem;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public SearchFragment() {
    }

    public static SearchFragment getInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        BottomNavigationViewEx bottomNavigationView = Objects.requireNonNull(getActivity())
                .findViewById(R.id.bottomNavigationMenu);
        menuItem = bottomNavigationView.getMenu().findItem(R.id.i_search);
        tabLayout = rootView.findViewById(R.id.tablayout);
        viewPager = rootView.findViewById(R.id.viewpager);
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getFragmentManager(),
                getActivity().getApplicationContext());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = pagerAdapter.getItem(position);
                if (fragment instanceof Updatable) {
                    ((Updatable) fragment).update();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        menuItem.setEnabled(true);
        super.onPause();
    }
}

