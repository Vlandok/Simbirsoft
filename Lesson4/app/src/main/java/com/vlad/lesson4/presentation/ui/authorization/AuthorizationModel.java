package com.vlad.lesson4.presentation.ui.authorization;


import rx.Observable;

public interface AuthorizationModel {
    Observable<String> changeTextEmail();

    Observable<String> changeTextPassword();

    Observable<Void> clickButtonEntry();

    Observable<Void> clickChangeVisibilityPassword();
}
