package com.vlad.lesson4.presentation.ui.base;

import android.os.Bundle;
import com.vlad.lesson4.androidx.MvpAppCompatFragment;
import androidx.annotation.Nullable;

public abstract class BaseFragment extends MvpAppCompatFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
