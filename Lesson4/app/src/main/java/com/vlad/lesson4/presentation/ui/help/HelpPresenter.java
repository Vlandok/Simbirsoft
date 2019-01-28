package com.vlad.lesson4.presentation.ui.help;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.EventCategories;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

public class HelpPresenter extends BasePresenter<HelpMvpView> {

    private static final String FILE_JSON = "categories.json";

    private EventCategories categories;

    public void onCreate(Context context, EventCategories listCategories) {
        checkViewAttached();
        categories = listCategories;
        if (categories == null) {
            getItemsCategory(context);
        } else {
            showCategories(getMvpView());
        }

    }

    private void getItemsCategory(Context context) {
        checkViewAttached();
        HelpTask helpTask = new HelpTask(context, getMvpView());
        helpTask.execute();
    }

    class HelpTask extends AsyncTask<Void, Void, Void> {

        private final WeakReference<Context> contextWeakReference;
        private final WeakReference<HelpMvpView> mvpViewWeakReference;

        HelpTask(Context context, HelpMvpView mvpView) {
            this.contextWeakReference = new WeakReference<>(context);
            this.mvpViewWeakReference = new WeakReference<>(mvpView);
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
            if (contextWeakReference.get() != null) {
                String data = JsonSupport.loadJSONFromAsset(contextWeakReference.get(), FILE_JSON);
                Type type = new TypeToken<EventCategories>() {
                }.getType();
                try {
                    categories = new Gson().fromJson(data, type);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (mvpViewWeakReference.get() != null) {
                super.onPostExecute(result);
                HelpMvpView mvpView = mvpViewWeakReference.get();
                showCategories(mvpView);
            }
        }
    }

    private void showCategories(HelpMvpView mvpView) {
        if (categories == null) {
            mvpView.showLoadingError();
            mvpView.onClickErrorButton();
        } else {
            mvpView.showItemsCategory(categories.getCategories());
            mvpView.onClickCategory();
        }
    }

    @Override
    protected void doUnsubscribe() {
    }
}


