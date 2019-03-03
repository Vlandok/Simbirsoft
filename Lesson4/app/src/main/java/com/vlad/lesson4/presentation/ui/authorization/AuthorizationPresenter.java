package com.vlad.lesson4.presentation.ui.authorization;

import com.arellomobile.mvp.InjectViewState;
import com.google.firebase.auth.FirebaseAuth;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.presentation.ui.base.BasePresenterMoxy;
import com.vlad.lesson4.utils.ValidEmail;

import java.util.concurrent.TimeUnit;

import durdinapps.rxfirebase2.RxFirebaseAuth;
import io.reactivex.disposables.Disposable;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

@InjectViewState
public class AuthorizationPresenter extends BasePresenterMoxy<AuthorizationMvpView> {

    private final static int MIN_LENGTH_PASSWORD = 6;
    private final static int TIME_WAIT_MILLISECONDS = 100;

    private Subscription editTextSub;
    private AuthorizationModel authorizationModel;
    private Observable<Boolean> emailObservable;
    private Observable<Boolean> passwordObservable;
    private Observable<Void> buttonObservable;
    private Observable<Void> clickButtonChangeVisible;
    private FirebaseAuth mAuth;
    private Disposable disposable;

    public AuthorizationPresenter(AuthorizationModel authorizationModel) {
        this.authorizationModel = authorizationModel;
    }

    @Override
    public void attachView(AuthorizationMvpView view) {
        super.attachView(view);
        clickChangeVisibilityPassword();
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
                            getViewState().setEntryButtonActive();
                        } else {
                            getViewState().setEntryButtonInactive();
                        }
                    });
        }
    }

    private void clickToButtonEntry() {
        buttonObservable = authorizationModel.clickButtonEntry();
        buttonObservable.subscribe(__ -> signInAccount());
    }

    private void clickChangeVisibilityPassword() {
        clickButtonChangeVisible = authorizationModel.clickChangeVisibilityPassword();
        clickButtonChangeVisible
                .subscribe(__ ->
                        getViewState().clickChangeVisibilityPassword());
    }

    private void signInAccount() {
        mAuth = FirebaseAuth.getInstance();
        disposable = RxFirebaseAuth.signInWithEmailAndPassword(mAuth,
                authorizationModel.changeTextEmail().toBlocking().first(),
                authorizationModel.changeTextPassword().toBlocking().first())
                .compose(applyBindingMaybe())
                .map(authResult -> authResult.getUser() != null)
                .doOnSubscribe(__ -> getViewState().showProgressView())
                .doOnEvent((__, throwable) -> getViewState().hideProgressView())
                .subscribe(logged -> getViewState().clickEntryButton(),
                        error -> getViewState().showAlertError());
    }
}


