package com.vlad.lesson4.presentation.ui.news;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

class NewsTask extends AsyncTask<Void, Void, Void> {

    private final WeakReference<NewsPresenter> newsPresenterWeakReference;
    private final WeakReference<Context> contextWeakReference;
    private final WeakReference<NewsMvpView> mvpViewWeakReference;

    NewsTask(Context context, NewsMvpView mvpView, NewsPresenter newsPresenter) {
        this.contextWeakReference = new WeakReference<>(context);
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
        if (contextWeakReference.get() != null && newsPresenterWeakReference.get() != null) {
            newsPresenterWeakReference.get().getEventCategoriesFromRealm();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mvpViewWeakReference.get() != null && newsPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            newsPresenterWeakReference.get().showNews(mvpViewWeakReference.get());
        }
    }
}
