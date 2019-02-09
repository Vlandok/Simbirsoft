package com.vlad.lesson4.presentation.ui.charityeventdetail;

import android.os.AsyncTask;

import com.vlad.lesson4.data.model.Event;

import java.lang.ref.WeakReference;

class CharityEventDetailTask extends AsyncTask<Void, Void, Void> {

    private Event event = new Event();
    private final WeakReference<CharityEventDetailPresenter> charityEventDetailPresenterWeakReference;
    private final WeakReference<CharityEventDetailMvpView> mvpViewWeakReference;
    private final WeakReference<Integer> idCategoryWeakReference;

    CharityEventDetailTask(CharityEventDetailMvpView mvpView
            , Integer idCategory, CharityEventDetailPresenter charityEventDetailPresenter) {
        this.charityEventDetailPresenterWeakReference = new WeakReference<>(charityEventDetailPresenter);
        this.mvpViewWeakReference = new WeakReference<>(mvpView);
        this.idCategoryWeakReference = new WeakReference<>(idCategory);
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
        if (idCategoryWeakReference.get() != null
                && charityEventDetailPresenterWeakReference.get() != null) {
            event = mvpViewWeakReference.get().getEventFromListEvents(idCategoryWeakReference.get(),
                    mvpViewWeakReference.get().getListEventsCategoryFromJson());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mvpViewWeakReference.get() != null
                && charityEventDetailPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            charityEventDetailPresenterWeakReference.get()
                    .showEventsDetail(mvpViewWeakReference.get(), event);
        }
    }
}
