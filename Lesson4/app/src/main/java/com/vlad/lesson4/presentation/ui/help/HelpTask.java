package com.vlad.lesson4.presentation.ui.help;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class HelpTask extends AsyncTask<Void, Void, Void> {

    private final WeakReference<HelpPresenter> helpPresenterWeakReference;
    private final WeakReference<Context> contextWeakReference;
    private final WeakReference<HelpMvpView> mvpViewWeakReference;

    HelpTask(Context context, HelpMvpView mvpView, HelpPresenter helpPresenter) {
        this.contextWeakReference = new WeakReference<>(context);
        this.mvpViewWeakReference = new WeakReference<>(mvpView);
        this.helpPresenterWeakReference = new WeakReference<>(helpPresenter);
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
        if (contextWeakReference.get() != null && helpPresenterWeakReference.get() != null) {
            helpPresenterWeakReference.get().jsonToEventCategories(contextWeakReference.get());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mvpViewWeakReference.get() != null && helpPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            HelpMvpView mvpView = mvpViewWeakReference.get();
            helpPresenterWeakReference.get().showCategories(mvpView);
        }
    }
}
