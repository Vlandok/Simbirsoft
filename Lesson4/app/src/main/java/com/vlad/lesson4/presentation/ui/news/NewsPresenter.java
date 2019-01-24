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

    public void onCreate(Context context) {
        checkViewAttached();
        getCharityEvents(context);
    }

    @Override
    protected void doUnsubscribe() {

    }

    private void getCharityEvents(Context context) {
        checkViewAttached();
        getMvpView().showProgressView();
        String data = JsonSupport.loadJSONFromAsset(context, FILE_JSON);
        Type type = new TypeToken<CharityEvent>() {
        }.getType();
        try {
            charityEvent = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        if (charityEvent == null) {
            getMvpView().showLoadingError();
        } else {
            getMvpView().showCharityEvents(charityEvent.getEvents());
            getMvpView().onClickEvent();
        }
    }
}
