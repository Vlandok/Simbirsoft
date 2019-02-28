package com.vlad.lesson4.presentation.ui.charityeventdetail;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CharityEventDetailPresenter extends BasePresenter<CharityEventDetailMvpView> {

    private Disposable disposable;

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

    public void onCreate(int id) {
        checkViewAttached();
        getEvent(id);
    }

    @Override
    protected void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void getEvent(int id) {
        checkViewAttached();
        disposable = eventProvider.getEvents()
                .compose(applyBinding())
                .compose(eventProvider.applyScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getMvpView().showProgressView())
                .doOnError(Throwable::printStackTrace)
                .onErrorReturn(__ -> itemsJsonProvider.getListEventsCategoryFromJson())
                .subscribe(events -> {
                    Event event = Event.getEventFromListEvents(id, events);
                    if (event != null) {
                        getMvpView().showEventDetail(event);
                    } else {
                        getMvpView().showLoadingError();
                        getMvpView().onClickErrorButton();
                    }
                });
    }
}
