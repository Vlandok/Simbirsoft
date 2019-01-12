package com.vlad.lesson4.presentation.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vlad.lesson4.MyApplication;
import com.vlad.lesson4.R;
import com.vlad.lesson4.di.ApplicationComponents;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ApplicationComponents getApplicationComponents() {
        return ((MyApplication) Objects.requireNonNull(getActivity()).getApplication()).getApplicationComponents();
    }
}
