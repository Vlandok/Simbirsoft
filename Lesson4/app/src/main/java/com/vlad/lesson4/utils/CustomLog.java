package com.vlad.lesson4.utils;

public final class CustomLog {

    static void showLogInfo(String tag, String msg){
        android.util.Log.i(tag, msg);
    }
}
