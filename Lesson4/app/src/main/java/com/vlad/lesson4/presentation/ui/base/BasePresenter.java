package com.vlad.lesson4.presentation.ui.base;

import com.vlad.lesson4.exception.MvpViewNotAttachedException;

import io.reactivex.MaybeTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;
    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        mMvpView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        doUnsubscribe();
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

    protected abstract void doUnsubscribe();

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }
}

