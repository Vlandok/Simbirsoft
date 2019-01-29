package com.vlad.lesson4.presentation.ui.news;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

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
        NewsTask newsTask = new NewsTask(context, getMvpView(), this);
        newsTask.execute();
    }

    @Override
    protected void doUnsubscribe() {

    }

    void jsonToCharityEvent(Context context) {
        String data = JsonSupport.loadJSONFromAsset(context, FILE_JSON);
        Type type = new TypeToken<CharityEvent>() {
        }.getType();
        try {
            charityEvent = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    void showNews(NewsMvpView mvpView) {
        if (charityEvent == null) {
            mvpView.showLoadingError();
            mvpView.onClickErrorButton();
        } else {
            mvpView.showCharityEvents(charityEvent.getEvents());
            mvpView.onClickEvent();
        }
    }
}
