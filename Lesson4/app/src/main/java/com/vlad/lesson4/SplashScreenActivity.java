package com.vlad.lesson4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView imageViewLogo;
    private TextView textViewProgressBar;
    private ImageView imageViewLogoMss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageViewLogo = findViewById(R.id.imageViewLogo);
        textViewProgressBar = findViewById(R.id.textViewProgressBar);
        imageViewLogoMss = findViewById(R.id.imageViewLogoMss);


        imageViewLogo.setImageResource(R.drawable.icon_logo);
        textViewProgressBar.setText(R.string.loading_with_points);
        imageViewLogoMss.setImageResource(R.drawable.logo_mss);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(HelpCategoriesActivity.TWO);
                    startActivity(HelpCategoriesActivity.createStartIntent(SplashScreenActivity.this));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


    }
}
