package com.vlad.lesson4.presentation.ui.base;

import android.os.Bundle;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.vlad.lesson4.MyApplication;
import com.vlad.lesson4.androidx.MvpAppCompatActivity;
import com.vlad.lesson4.di.component.ActivityComponent;
import com.vlad.lesson4.di.component.DaggerActivityComponent;
import com.vlad.lesson4.di.module.ActivityModule;

public abstract class BaseActivityMoxy extends MvpAppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MyApplication) getApplication()).getApplicationComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
