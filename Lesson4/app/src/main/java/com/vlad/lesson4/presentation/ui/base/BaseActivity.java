package com.vlad.lesson4.presentation.ui.base;

import android.os.Bundle;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.vlad.lesson4.androidx.MvpAppCompatActivity;

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
    }
}
