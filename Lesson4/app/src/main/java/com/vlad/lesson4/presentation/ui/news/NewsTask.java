package com.vlad.lesson4.presentation.ui.news;

import android.os.AsyncTask;

import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.db.repository.NewsRepository;

import java.lang.ref.WeakReference;

class NewsTask extends AsyncTask<Void, Void, Void> {

    private CharityEvent charityEvent = new CharityEvent();
    private final WeakReference<NewsPresenter> newsPresenterWeakReference;
    private final WeakReference<NewsMvpView> mvpViewWeakReference;

    NewsTask(NewsMvpView mvpView, NewsPresenter newsPresenter) {
        this.mvpViewWeakReference = new WeakReference<>(mvpView);
        this.newsPresenterWeakReference = new WeakReference<>(newsPresenter);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mvpViewWeakReference.get() != null) {
            mvpViewWeakReference.get().showProgressView();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        charityEvent = NewsRepository.getInstance().getEventCategoriesFromRealm();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mvpViewWeakReference.get() != null && newsPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            newsPresenterWeakReference.get().showNews(mvpViewWeakReference.get(), charityEvent);
        }
    }
}
