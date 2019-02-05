package com.vlad.lesson4.presentation.ui.news;

import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

public class NewsPresenter extends BasePresenter<NewsMvpView> {

    public void onCreate() {
        checkViewAttached();
        getCharityEvents();
    }

    private void getCharityEvents() {
        checkViewAttached();
        NewsTask newsTask = new NewsTask(getMvpView(), this);
        newsTask.execute();
    }

    @Override
    protected void doUnsubscribe() {

    }

    void showNews(NewsMvpView mvpView, CharityEvent charityEvent) {
        if (charityEvent == null) {
            mvpView.showLoadingError();
            mvpView.onClickErrorButton();
        } else {
            mvpView.showCharityEvents(charityEvent.getEvents());
            mvpView.onClickEvent();
        }
    }
}
