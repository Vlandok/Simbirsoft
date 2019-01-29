package com.vlad.lesson4.presentation.ui.сharityevents;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.reflect.Type;
import java.util.List;

public class CharityEventsPresenter extends BasePresenter<CharityEventsMvpView> {

    private static final String FILE_JSON = "events.json";

    private CharityEvent charityEvent;

    public void onCreate(Context context, CharityEvent events) {
        checkViewAttached();
        charityEvent = events;
        if (charityEvent == null) {
            getCharityEvents(context);
        } else {
            showEvents(getMvpView());
        }
    }

    @Override
    protected void doUnsubscribe() {

    }

    private void getCharityEvents(Context context) {
        checkViewAttached();
        CharityEventsTask charityEventsTask = new CharityEventsTask(context, getMvpView(), this);
        charityEventsTask.execute();
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

    void showEvents(CharityEventsMvpView mvpView) {
        if (charityEvent == null) {
            mvpView.showLoadingError();
        } else {
            mvpView.setTitleToolbar();
            List<Event> events = getMvpView().getEventsCategory(charityEvent.getEvents());
            mvpView.showCharityEvents(events);
            mvpView.onClickEvent();
        }
    }
}
