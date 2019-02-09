package com.vlad.lesson4.presentation.ui.сharityevents;

import android.os.AsyncTask;

import com.vlad.lesson4.data.model.Event;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class CharityEventsTask extends AsyncTask<Void, Void, Void> {

    private List<Event> events = new ArrayList<>();
    private final WeakReference<CharityEventsPresenter> charityEventsPresenterWeakReference;
    private final WeakReference<CharityEventsMvpView> mvpViewWeakReference;

    CharityEventsTask(CharityEventsMvpView mvpView,
                      CharityEventsPresenter charityEventsPresenter) {
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
        if (mvpViewWeakReference.get() != null) {
            events = mvpViewWeakReference.get().getListEventsCategoryFromJson();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (charityEventsPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            charityEventsPresenterWeakReference.get().showEvents( events);
        }
    }
}
