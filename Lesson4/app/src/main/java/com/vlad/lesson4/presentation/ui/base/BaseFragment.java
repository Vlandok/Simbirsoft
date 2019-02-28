package com.vlad.lesson4.presentation.ui.base;

import android.os.Bundle;

import com.vlad.lesson4.MyApplication;
import com.vlad.lesson4.di.component.ActivityComponent;
//import com.vlad.lesson4.di.component.DaggerActivityComponent;
import com.vlad.lesson4.di.component.DaggerActivityComponent;
import com.vlad.lesson4.di.module.ActivityModule;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private ActivityComponent activityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule((AppCompatActivity) getActivity()))
                .applicationComponent(((MyApplication) Objects.requireNonNull(getActivity())
                        .getApplication()).getApplicationComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

//    public ApplicationComponents getApplicationComponents() {
//        return ((MyApplication) Objects.requireNonNull(getActivity()).getApplication()).getApplicationComponents();
//    }
}
