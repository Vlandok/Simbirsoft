package com.vlad.lesson4.presentation.ui.charityeventdetail;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class CharityEventDetailPresenter extends BasePresenter<CharityEventDetailMvpView> {

    @NonNull
    private EventProvider eventProvider;
    @NonNull
    private ItemsJsonProvider itemsJsonProvider;

    @Inject
    public CharityEventDetailPresenter(@NonNull EventProvider eventProvider,
                                       @NonNull ItemsJsonProvider itemsJsonProvider) {
        this.eventProvider = eventProvider;
        this.itemsJsonProvider = itemsJsonProvider;
    }

    @SuppressLint("CheckResult")
    void getEvent(int id) {
        eventProvider.getEvents()
                .compose(applyBinding())
                .compose(eventProvider.applyScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getViewState().showProgressView())
                .doOnError(Throwable::printStackTrace)
                .onErrorReturn(__ -> itemsJsonProvider.getListEventsCategoryFromJson())
                .subscribe(events -> {
                    Event event = Event.getEventFromListEvents(id, events);
                    if (event != null) {
                        getViewState().showEventDetail(event);
                    } else {
                        getViewState().showLoadingError();
                        getViewState().onClickErrorButton();
                    }
                });
    }
}
