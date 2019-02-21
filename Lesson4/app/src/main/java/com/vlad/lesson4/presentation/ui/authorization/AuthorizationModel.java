package com.vlad.lesson4.presentation.ui.authorization;


import android.view.MotionEvent;

import rx.Observable;

public interface AuthorizationModel {
    Observable<String> changeTextEmail();

    Observable<String> changeTextPassword();

    Observable<Void> clickButtonEntry();

    Observable<MotionEvent> clickChangeVisibilityPassword();
}
