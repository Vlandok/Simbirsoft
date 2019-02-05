package com.vlad.lesson4.presentation.ui.сharityevents;

import android.os.AsyncTask;

import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.db.repository.CharityEventsRepository;

import java.lang.ref.WeakReference;

class CharityEventsTask extends AsyncTask<Void, Void, Void> {

    private CharityEvent charityEvent = new CharityEvent();
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
        charityEvent = CharityEventsRepository.getInstance().getEventCategoriesFromRealm();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mvpViewWeakReference.get() != null && charityEventsPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            charityEventsPresenterWeakReference.get()
                    .showEvents(mvpViewWeakReference.get(), charityEvent);
        }
    }
}
