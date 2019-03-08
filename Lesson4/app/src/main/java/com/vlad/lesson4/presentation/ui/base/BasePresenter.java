package com.vlad.lesson4.presentation.ui.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T extends MvpView> extends MvpPresenter<T> {

    private CompositeDisposable compositeDisposable;
    private CompositeSubscription compositeSubscription;

    @Override
    public void attachView(T view) {
        super.attachView(view);
        compositeDisposable = new CompositeDisposable();
        compositeSubscription = new CompositeSubscription();
    }

    protected <Y> SingleTransformer<Y, Y> applyBinding() {
        return upstream -> upstream
                .doOnSubscribe((Consumer<Disposable>) this::bindToLifecycle);
    }

    protected <Y> MaybeTransformer<Y, Y> applyBindingMaybe() {
        return upstream -> upstream
                .doOnSubscribe((Consumer<Disposable>) this::bindToLifecycle);
    }

    private void bindToLifecycle(Disposable d) {
        compositeDisposable.add(d);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    protected void bindToLifecycle (Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    protected void doUnsubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        if (compositeSubscription != null) {
            compositeSubscription.clear();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        doUnsubscribe();
    }
}

