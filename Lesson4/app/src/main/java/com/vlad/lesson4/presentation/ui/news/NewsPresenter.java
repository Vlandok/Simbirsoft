package com.vlad.lesson4.presentation.ui.news;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

public class NewsPresenter extends BasePresenter<NewsMvpView> {

    private static final String FILE_JSON = "events.json";

    private CharityEvent charityEvent;

    public void onCreate(Context context, CharityEvent events) {
        checkViewAttached();
        charityEvent = events;
        if (charityEvent == null) {
            getCharityEvents(context);
        } else {
            showNews(getMvpView());
        }
    }

    private void getCharityEvents(Context context) {
        checkViewAttached();
        NewsTask newsTask = new NewsTask(context, getMvpView());
        newsTask.execute();
    }

    @Override
    protected void doUnsubscribe() {

    }

    class NewsTask extends AsyncTask<Void, Void, Void> {

        private final WeakReference<Context> contextWeakReference;
        private final WeakReference<NewsMvpView> mvpViewWeakReference;

        NewsTask(Context context, NewsMvpView mvpView) {
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
                Type type = new TypeToken<CharityEvent>() {
                }.getType();
                try {
                    charityEvent = new Gson().fromJson(data, type);
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
                NewsMvpView mvpView = mvpViewWeakReference.get();
                showNews(mvpView);
            }
        }
    }

    private void showNews(NewsMvpView mvpView) {
        if (charityEvent == null) {
            mvpView.showLoadingError();
            mvpView.onClickErrorButton();
        } else {
            mvpView.showCharityEvents(charityEvent.getEvents());
            mvpView.onClickEvent();
        }
    }
}
