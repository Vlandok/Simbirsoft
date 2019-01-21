package com.vlad.lesson4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(HelpCategoriesActivity.TWO);
                startActivity(HelpCategoriesActivity.createStartIntent(SplashScreenActivity.this));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}
