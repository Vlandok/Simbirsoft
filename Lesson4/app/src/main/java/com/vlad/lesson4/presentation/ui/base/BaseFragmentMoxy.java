package com.vlad.lesson4.presentation.ui.base;

import android.os.Bundle;

import com.vlad.lesson4.MyApplication;
import com.vlad.lesson4.androidx.MvpAppCompatActivity;
import com.vlad.lesson4.androidx.MvpAppCompatFragment;
import com.vlad.lesson4.di.component.ActivityComponent;
import com.vlad.lesson4.di.component.DaggerActivityComponent;
import com.vlad.lesson4.di.module.ActivityModule;

import java.util.Objects;

import androidx.annotation.Nullable;

public abstract class BaseFragmentMoxy extends MvpAppCompatFragment {

    private ActivityComponent activityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule((MvpAppCompatActivity) getActivity()))
                .applicationComponent(((MyApplication) Objects.requireNonNull(getActivity())
                        .getApplication()).getApplicationComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
