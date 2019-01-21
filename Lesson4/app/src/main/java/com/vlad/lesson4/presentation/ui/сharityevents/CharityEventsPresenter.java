package com.vlad.lesson4.presentation.ui.сharityevents;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.reflect.Type;
import java.util.List;

public class CharityEventsPresenter extends BasePresenter<CharityEventsMvpView> {

    private static final String FILE_JSON = "events.json";

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
        CharityEvent charityEvent = new Gson().fromJson(data, type);
        if (charityEvent == null) {
            getMvpView().showLoadingError();
        } else {
            List<Event> events = getMvpView().getEventsCategory(charityEvent.getEvents());
            getMvpView().showCharityEvents(events);
            getMvpView().onClickEvent();
        }
    }
}
