package com.vlad.lesson4.presentation.ui.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.vlad.lesson4.exception.MvpViewNotAttachedException;

import io.reactivex.MaybeTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BasePresenterMoxy<T extends MvpView> extends MvpPresenter<T> {

    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(T view) {
        super.attachView(view);
        compositeDisposable = new CompositeDisposable();
    }

    protected <T> SingleTransformer<T, T> applyBinding() {
        return upstream -> upstream
                .doOnSubscribe((Consumer<Disposable>) this::bindToLifecycle);
    }

    protected <T> MaybeTransformer<T, T> applyBindingMaybe() {
        return upstream -> upstream
                .doOnSubscribe((Consumer<Disposable>) this::bindToLifecycle);
    }

    protected void bindToLifecycle(Disposable d) {
        compositeDisposable.add(d);
    }

    protected void doUnsubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        doUnsubscribe();
    }
}

