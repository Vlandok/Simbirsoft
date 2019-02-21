package com.vlad.lesson4.domain.provider;

import android.view.MotionEvent;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.vlad.lesson4.presentation.ui.authorization.AuthorizationModel;
import com.vlad.lesson4.presentation.ui.authorization.AuthorizationViewHolder;

import rx.Observable;

public class AuthorizationProvider implements AuthorizationModel {
    private final AuthorizationViewHolder viewHolder;

    public AuthorizationProvider(final AuthorizationViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    @Override
    public Observable<String> changeTextEmail() {
        return RxTextView.textChanges(viewHolder.editTextEmail)
                .map(charSequence -> charSequence.toString().trim());
    }

    @Override
    public Observable<String> changeTextPassword() {
        return RxTextView.textChanges(viewHolder.editTextPassword)
                .map(CharSequence::toString);
    }

    @Override
    public Observable<Void> clickButtonEntry() {
        return RxView.clicks(viewHolder.buttonEntry);
    }

    @Override
    public Observable<MotionEvent> clickChangeVisibilityPassword() {
        return RxView.touches(viewHolder.editTextPassword);
    }
}
