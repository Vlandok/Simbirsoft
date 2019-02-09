package com.vlad.lesson4.presentation.ui.news;

import android.os.AsyncTask;

import com.vlad.lesson4.data.model.Event;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class NewsTask extends AsyncTask<Void, Void, Void> {

    private List<Event> events = new ArrayList<>();
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
        if (mvpViewWeakReference.get() != null) {
            events = mvpViewWeakReference.get().getListEventsFromJson();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mvpViewWeakReference.get() != null && newsPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            newsPresenterWeakReference.get().showNews(mvpViewWeakReference.get(), events);
        }
    }
}
