package com.vlad.lesson4.presentation.ui.news;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class NewsPresenter extends BasePresenter<NewsMvpView> {

    @NonNull
    private EventProvider eventProvider;
    @NonNull
    private ItemsJsonProvider itemsJsonProvider;

    @Inject
    public NewsPresenter(@NonNull EventProvider eventProvider,
                         @NonNull ItemsJsonProvider itemsJsonProvider) {
        this.eventProvider = eventProvider;
        this.itemsJsonProvider = itemsJsonProvider;
    }

    @Override
    public void attachView(NewsMvpView view) {
        super.attachView(view);
        getCharityEvents();
    }

    @SuppressLint("CheckResult")
    void getCharityEvents() {
        eventProvider.getEvents()
                .compose(applyBinding())
                .compose(eventProvider.applyScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getViewState().showProgressView())
                .doOnError(Throwable::printStackTrace)
                .onErrorReturn(__ -> itemsJsonProvider.getListEventsCategoryFromJson())
                .subscribe(events -> {
                    getViewState().showCharityEvents(events);
                    getViewState().onClickEvent();
                });
    }
}
