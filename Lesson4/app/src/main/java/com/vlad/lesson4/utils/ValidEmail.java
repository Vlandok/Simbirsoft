package com.vlad.lesson4.utils;

import android.text.TextUtils;

public class ValidEmail {
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
