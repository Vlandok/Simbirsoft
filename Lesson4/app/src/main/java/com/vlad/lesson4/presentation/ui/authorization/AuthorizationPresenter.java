package com.vlad.lesson4.presentation.ui.authorization;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.google.firebase.auth.FirebaseAuth;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.ValidEmail;

import java.util.concurrent.TimeUnit;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

@InjectViewState
public class AuthorizationPresenter extends BasePresenter<AuthorizationMvpView> {

    private final static int MIN_LENGTH_PASSWORD = 6;
    private final static int TIME_WAIT_MILLISECONDS = 100;

    private Observable<Boolean> emailObservable;
    private Observable<Boolean> passwordObservable;
    private Observable<String> emailObservableString;
    private Observable<String> passwordObservableString;
    private FirebaseAuth mAuth;

    void initEmailObservable(Observable<CharSequence> observable) {
        emailObservableString = observable.map(charSequence -> charSequence.toString().trim());
        emailObservable = emailObservableString
                .debounce(TIME_WAIT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(ValidEmail::isValidEmail);
    }

    void initPasswordObservable(Observable<CharSequence> observable) {
        passwordObservableString = observable.map(CharSequence::toString);
        passwordObservable = passwordObservableString
                .debounce(TIME_WAIT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(password -> password.length() >= MIN_LENGTH_PASSWORD);
    }

    void checkValidateEmailAndPass() {
        if (emailObservable != null && passwordObservable != null) {
            Observable.combineLatest(emailObservable, passwordObservable,
                    (validEmail, validPassword) -> validEmail && validPassword)
                    .subscribe(enabled -> {
                        if (enabled) {
                            getViewState().setEntryButtonActive();
                        } else {
                            getViewState().setEntryButtonInactive();
                        }
                    });
        }
    }

    void clickToButtonEntry(Observable<Void> observable) {
        observable.subscribe(__ -> signInAccount());
    }

    void clickChangeVisibilityPassword(Observable<Void> observable) {
        observable.subscribe(__ ->
                getViewState().clickChangeVisibilityPassword());
    }

    @SuppressLint("CheckResult")
    private void signInAccount() {
        mAuth = FirebaseAuth.getInstance();
        RxFirebaseAuth.signInWithEmailAndPassword(mAuth,
                emailObservableString.toBlocking().first(),
                passwordObservableString.toBlocking().first())
                .compose(applyBindingMaybe())
                .map(authResult -> authResult.getUser() != null)
                .doOnSubscribe(__ -> getViewState().showProgressView())
                .doOnEvent((__, throwable) -> getViewState().hideProgressView())
                .subscribe(logged -> getViewState().clickEntryButton(),
                        error -> getViewState().showAlertError());
    }
}


