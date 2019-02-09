package com.vlad.lesson4.presentation.ui.news;

import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter extends BasePresenter<NewsMvpView> {

    @Nullable
    private Disposable disposable;
    @NonNull
    private EventProvider eventProvider;

    public NewsPresenter(@NonNull EventProvider eventProvider) {
        this.eventProvider = eventProvider;
    }

    public void onCreate() {
        checkViewAttached();
        getCharityEvents();
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
                            NewsTask newsTask = new NewsTask(getMvpView(), this);
                            newsTask.execute();
                            error.printStackTrace();
//                            getMvpView().showLoadingError();
//                            getMvpView().onClickErrorButton();
                        });
    }

    @Override
    protected void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    void showNews(NewsMvpView mvpView, List<Event> events) {
        if (events == null) {
            mvpView.showLoadingError();
            mvpView.onClickErrorButton();
        } else {
            mvpView.showCharityEvents(events);
            mvpView.onClickEvent();
        }
    }
}
