package com.vlad.lesson4.presentation.ui.base;

import android.os.Bundle;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.vlad.lesson4.MyApplication;
import com.vlad.lesson4.di.ApplicationComponents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
    }

    public ApplicationComponents getApplicationComponents() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        return ((MyApplication) getApplication()).getApplicationComponents();
    }
}
