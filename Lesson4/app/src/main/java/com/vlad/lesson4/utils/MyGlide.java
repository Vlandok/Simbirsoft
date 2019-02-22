package com.vlad.lesson4.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

public final class MyGlide {
    public static void loadImage(Context context, String urlImage, int positionImage,
                                 ImageView imageViewOne, ImageView imageViewTwo,
                                 ImageView imageViewThree) {
        Glide.with(context)
                .load(urlImage)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, Transition<? super Drawable> transition) {
                        switch (positionImage) {
                            case 0:
                                imageViewOne.setImageDrawable(resource);
                                break;
                            case 1:
                                imageViewTwo.setImageDrawable(resource);
                                break;
                            case 2:
                                imageViewThree.setImageDrawable(resource);
                                break;
                        }
                    }
                });
    }

    public static void loadImage(Context context, String urlImage, AppCompatImageView imageView) {
        Glide.with(context)
                .load(urlImage)
                .into(imageView);
    }

    public static void loadImageWithGetDrawable(Context context, String urlImage,
                                                AppCompatImageView imageView) {
        Glide.with(context)
                .load(urlImage)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                Transition<? super Drawable> transition) {
                        imageView.setBackground(resource);
                    }
                });
    }

    public static void loadImage(Fragment fragment, String urlImage, ImageView imageViewUser) {
        Glide.with(fragment)
                .load(urlImage)
                .into(imageViewUser);
    }
}
