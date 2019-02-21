package com.vlad.lesson4.presentation.ui.authorization;

import android.view.MotionEvent;

import com.google.firebase.auth.FirebaseAuth;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.ValidEmail;

import java.util.concurrent.TimeUnit;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.disposables.Disposable;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


public class AuthorizationPresenter extends BasePresenter<AuthorizationMvpView> {

    private final static int MIN_LENGTH_PASSWORD = 6;
    private final static int TIME_WAIT_MILLISECONDS = 200;

    private Subscription editTextSub;
    private AuthorizationModel authorizationModel;
    private Observable<Boolean> emailObservable;
    private Observable<Boolean> passwordObservable;
    private Observable<Void> buttonObservable;
    private Observable<MotionEvent> clickDrawableChangeVisible;
    private FirebaseAuth mAuth;
    private Disposable disposable;


    public AuthorizationPresenter(AuthorizationModel authorizationModel) {
        this.authorizationModel = authorizationModel;
    }

    public void onCreate() {
        checkViewAttached();
//        clickChangeVisibilityPassword();
        getMvpView().clickChangeVisibilityPassword();
        clickToButtonEntry();
        initEmailObservable();
        initPasswordObservable();
        checkValidateEmailAndPass();
    }

    private void initEmailObservable() {
        emailObservable = authorizationModel.changeTextEmail()
                .debounce(TIME_WAIT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(ValidEmail::isValidEmail);
    }

    private void initPasswordObservable() {
        passwordObservable = authorizationModel.changeTextPassword()
                .debounce(TIME_WAIT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(password -> password.length() >= MIN_LENGTH_PASSWORD);
    }

    private void checkValidateEmailAndPass() {
        if (emailObservable != null && passwordObservable != null) {
            editTextSub = Observable.combineLatest(emailObservable, passwordObservable,
                    (validEmail, validPassword) -> validEmail && validPassword)
                    .subscribe(enabled -> {
                        if (enabled) {
                            getMvpView().setEntryButtonActive();
                        } else {
                            getMvpView().setEntryButtonInactive();
                        }
                    });
        }
    }

    private void clickToButtonEntry() {
        buttonObservable = authorizationModel.clickButtonEntry();
        buttonObservable.subscribe(__ -> signInAccount());
    }

//    private void clickChangeVisibilityPassword() {
//        clickDrawableChangeVisible = authorizationModel.clickChangeVisibilityPassword();
//        clickDrawableChangeVisible.subscribe(motionEvent ->
//                getMvpView().clickChangeVisibilityPassword(motionEvent));
//    }

    private void signInAccount() {
        mAuth = FirebaseAuth.getInstance();
        disposable = RxFirebaseAuth.signInWithEmailAndPassword(mAuth,
                authorizationModel.changeTextEmail().toBlocking().first(),
                authorizationModel.changeTextPassword().toBlocking().first())
                .map(authResult -> authResult.getUser() != null)
                .doOnSubscribe(__ -> getMvpView().showProgressView())
                .doOnEvent((__, throwable) -> getMvpView().hideProgressView())
                .subscribe(logged -> getMvpView().clickEntryButton(),
                        error -> getMvpView().showAlertError());
    }

    @Override
    protected void doUnsubscribe() {
        if (editTextSub != null && disposable != null) {
            editTextSub.unsubscribe();
            disposable.dispose();
        }
    }
}


