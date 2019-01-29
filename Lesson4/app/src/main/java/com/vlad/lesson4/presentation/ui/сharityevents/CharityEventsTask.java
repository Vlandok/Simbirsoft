package com.vlad.lesson4.presentation.ui.сharityevents;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

class CharityEventsTask extends AsyncTask<Void, Void, Void> {

    private final WeakReference<CharityEventsPresenter> charityEventsPresenterWeakReference;
    private final WeakReference<Context> contextWeakReference;
    private final WeakReference<CharityEventsMvpView> mvpViewWeakReference;

    CharityEventsTask(Context context, CharityEventsMvpView mvpView,
                      CharityEventsPresenter charityEventsPresenter) {
        this.contextWeakReference = new WeakReference<>(context);
        this.mvpViewWeakReference = new WeakReference<>(mvpView);
        this.charityEventsPresenterWeakReference = new WeakReference<>(charityEventsPresenter);
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
        if (contextWeakReference.get() != null && charityEventsPresenterWeakReference.get() != null) {
            charityEventsPresenterWeakReference.get().jsonToCharityEvent(contextWeakReference.get());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mvpViewWeakReference.get() != null && charityEventsPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            CharityEventsMvpView mvpView = mvpViewWeakReference.get();
            charityEventsPresenterWeakReference.get().showEvents(mvpView);
        }
    }
}
