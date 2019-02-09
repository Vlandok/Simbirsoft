package com.vlad.lesson4.presentation.ui.help;

import android.os.AsyncTask;

import com.vlad.lesson4.data.model.Category;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class HelpTask extends AsyncTask<Void, Void, Void> {

    private List<Category> categories = new ArrayList<>();
    private final WeakReference<HelpPresenter> helpPresenterWeakReference;
    private final WeakReference<HelpMvpView> mvpViewWeakReference;

    HelpTask(HelpMvpView mvpView, HelpPresenter helpPresenter) {
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
        if (mvpViewWeakReference.get() != null) {
            categories = mvpViewWeakReference.get().getListCategoriesFromJson();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if (mvpViewWeakReference.get() != null && helpPresenterWeakReference.get() != null) {
            super.onPostExecute(result);
            helpPresenterWeakReference.get().showCategories(mvpViewWeakReference.get(), categories);
        }
    }
}
