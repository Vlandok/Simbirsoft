package com.vlad.lesson4.presentation.ui.charityeventdetail;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.reflect.Type;

import static com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailActivity.DEFAULT_VALUE;

public class CharityEventDetailPresenter extends BasePresenter<CharityEventDetailMvpView> {

    private static final String FILE_JSON = "events.json";

    private CharityEvent charityEvent;

    public void onCreate(Context context, int id, CharityEvent events) {
        checkViewAttached();
        charityEvent = events;
        if (charityEvent == null) {
            getEvent(context, id);
        } else {
            showEventsDetail(getMvpView(), id);
        }
    }

    @Override
    protected void doUnsubscribe() {

    }

    private void getEvent(Context context, int id) {
        checkViewAttached();
        CharityEventDetailTask charityEventDetailTask
                = new CharityEventDetailTask(context, getMvpView(), id, this);
        charityEventDetailTask.execute();
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

    void showEventsDetail(CharityEventDetailMvpView mvpView, Integer id) {
        if (charityEvent == null && id == DEFAULT_VALUE) {
            mvpView.showLoadingError();
        } else {
            Event event = getMvpView().getEvent(charityEvent, id);
            mvpView.showEventDetail(event);
        }
    }

}
