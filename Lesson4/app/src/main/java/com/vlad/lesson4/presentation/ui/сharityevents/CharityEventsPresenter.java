package com.vlad.lesson4.presentation.ui.сharityevents;

import com.arellomobile.mvp.InjectViewState;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.presentation.ui.base.BasePresenterMoxy;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
@InjectViewState
public class CharityEventsPresenter extends BasePresenterMoxy<CharityEventsMvpView> {

    private Disposable disposable;
    @NonNull
    private EventProvider eventProvider;
    @NonNull
    private ItemsJsonProvider itemsJsonProvider;

    public CharityEventsPresenter(@NonNull EventProvider eventProvider,
                                  @NonNull ItemsJsonProvider itemsJsonProvider) {
        this.eventProvider = eventProvider;
        this.itemsJsonProvider = itemsJsonProvider;
    }

    public void getCharityEvents(int idCategory) {
        disposable = eventProvider.getEvents()
                .compose(applyBinding())
                .compose(eventProvider.applyScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getViewState().showProgressView())
                .doOnError(Throwable::printStackTrace)
                .onErrorReturn(__ -> itemsJsonProvider.getListEventsCategoryFromJson())
                .subscribe(events -> {
                    List<Event> eventsCategory = Event.getEventsCategory(events, idCategory);
                    getViewState().setTitleToolbar();
                    getViewState().showCharityEvents(eventsCategory);
                    getViewState().onClickEvent();
                });
    }
}
