package com.vlad.lesson4.presentation.ui.charityeventdetail;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CharityEventDetailPresenter extends BasePresenter<CharityEventDetailMvpView> {

    @Nullable
    private Disposable disposable;

    @NonNull
    private EventProvider eventProvider;

    public CharityEventDetailPresenter(@NonNull EventProvider eventProvider) {
        this.eventProvider = eventProvider;
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
        getMvpView().showProgressView();
        disposable = eventProvider.getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                            Event event = getMvpView().getEventFromListEvents(id, events);
                            if (event != null) {
                                getMvpView().showEventDetail(event);
                            } else {
                                getMvpView().showLoadingError();
                                getMvpView().onClickErrorButton();
                            }
                        },
                        error -> {
                            checkViewAttached();
                            CharityEventDetailTask charityEventDetailTask
                                    = new CharityEventDetailTask(getMvpView(), id, this);
                            charityEventDetailTask.execute();
                            error.printStackTrace();
//                            getMvpView().showLoadingError();
//                            getMvpView().onClickErrorButton();
                        });
    }

    void showEventsDetail(CharityEventDetailMvpView mvpView, Event event) {
        if (event == null) {
            mvpView.showLoadingError();
        } else {
            mvpView.showEventDetail(event);
        }
    }
}
