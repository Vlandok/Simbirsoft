package com.vlad.lesson4.presentation.ui.сharityevents;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CharityEventsPresenter extends BasePresenter<CharityEventsMvpView> {

    private Disposable disposable;
    @NonNull
    private EventProvider eventProvider;
    @NonNull
    private ItemsJsonProvider itemsJsonProvider;

    @Inject
    public CharityEventsPresenter(@NonNull EventProvider eventProvider,
                                  @NonNull ItemsJsonProvider itemsJsonProvider) {
        this.eventProvider = eventProvider;
        this.itemsJsonProvider = itemsJsonProvider;
    }

    public void onCreate(int idCategory) {
        checkViewAttached();
        getCharityEvents(idCategory);
    }

    @Override
    protected void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void getCharityEvents(int idCategory) {
        disposable = eventProvider.getEvents()
                .compose(applyBinding())
                .compose(eventProvider.applyScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getMvpView().showProgressView())
                .doOnError(Throwable::printStackTrace)
                .onErrorReturn(__ -> itemsJsonProvider.getListEventsCategoryFromJson())
                .subscribe(events -> {
                    List<Event> eventsCategory = Event.getEventsCategory(events, idCategory);
                    getMvpView().setTitleToolbar();
                    getMvpView().showCharityEvents(eventsCategory);
                    getMvpView().onClickEvent();
                });
    }
}
