package com.vlad.lesson4.presentation.ui.news;

import com.arellomobile.mvp.InjectViewState;
import com.vlad.lesson4.MyApplication;
import com.vlad.lesson4.data.remote.api.FirebaseApi;
import com.vlad.lesson4.di.component.ActivityComponent;
import com.vlad.lesson4.domain.provider.EventProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BaseActivityMoxy;
import com.vlad.lesson4.presentation.ui.base.BaseFragmentMoxy;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.presentation.ui.base.BasePresenterMoxy;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class NewsPresenter extends BasePresenterMoxy<NewsMvpView> {

    private Disposable disposable;
    @NonNull
    private EventProvider eventProvider;
    @NonNull
    private ItemsJsonProvider itemsJsonProvider;

    public NewsPresenter(@NonNull EventProvider eventProvider,
                         @NonNull ItemsJsonProvider itemsJsonProvider) {
        this.eventProvider = eventProvider;
        this.itemsJsonProvider = itemsJsonProvider;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getCharityEvents();
    }

    public void getCharityEvents() {
        disposable = eventProvider.getEvents()
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
