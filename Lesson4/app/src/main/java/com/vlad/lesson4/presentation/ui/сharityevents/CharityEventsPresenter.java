package com.vlad.lesson4.presentation.ui.сharityevents;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CharityEventsPresenter extends BasePresenter<CharityEventsMvpView> {

    @Nullable
    private Disposable disposable;
    @NonNull
    private EventProvider eventProvider;

    public CharityEventsPresenter(@NonNull EventProvider eventProvider) {
        this.eventProvider = eventProvider;
    }

    public void onCreate() {
        checkViewAttached();
        getCharityEvents();
    }

    @Override
    protected void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void getCharityEvents() {
        checkViewAttached();
        getMvpView().showProgressView();
        disposable = eventProvider.getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                            getMvpView().showCharityEvents(events);
                            getMvpView().onClickEvent();
                        },
                        error -> {
                            checkViewAttached();
                            CharityEventsTask charityEventsTask = new CharityEventsTask(getMvpView(), this);
                            charityEventsTask.execute();
                            error.printStackTrace();
//                            getMvpView().showLoadingError();
//                            getMvpView().onClickErrorButton();
                        });
    }

    public void showEvents(List<Event> events) {
        checkViewAttached();
        if (events.isEmpty()) {
            getMvpView().showLoadingError();
        } else {
            getMvpView().setTitleToolbar();
            getMvpView().showCharityEvents(getMvpView().getEventsCategory(events));
            getMvpView().onClickEvent();
        }
    }
}
