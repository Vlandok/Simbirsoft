package com.vlad.lesson4.presentation.ui.help;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class HelpTask extends AsyncTask<Void, Void, Void> {

    private final WeakReference<HelpPresenter> helpPresenterWeakReference;
    private final WeakReference<Context> contextWeakReference;
    private final WeakReference<HelpMvpView> mvpViewWeakReference;

    HelpTask(Context context, HelpMvpView mvpView, HelpPresenter helpPresenter) {
        contextWeakReference = new WeakReference<>(context);
        mvpViewWeakReference = new WeakReference<>(mvpView);
        helpPresenterWeakReference = new WeakReference<>(helpPresenter);
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
        if (helpPresenterWeakReference.get() != null) {
            helpPresenterWeakReference.get().getEventCategoriesFromRealm();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mvpViewWeakReference.get() != null && helpPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            helpPresenterWeakReference.get().showCategories(mvpViewWeakReference.get());
        }
    }
}
