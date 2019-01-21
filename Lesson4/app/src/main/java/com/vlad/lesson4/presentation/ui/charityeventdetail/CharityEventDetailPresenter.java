package com.vlad.lesson4.presentation.ui.charityeventdetail;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.reflect.Type;

import static com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailActivity.DEFAULT_VALUE;

public class CharityEventDetailPresenter extends BasePresenter<CharityEventDetailMvpView> {

    private static final String FILE_JSON = "events.json";

    public void onCreate(Context context , int id) {
        checkViewAttached();
        getEvent(context, id);
    }
    @Override
    protected void doUnsubscribe() {

    }

    private void getEvent(Context context, int id) {
        checkViewAttached();
        String data = JsonSupport.loadJSONFromAsset(context, FILE_JSON);
        Type type = new TypeToken<CharityEvent>() {
        }.getType();
        CharityEvent charityEvent = new Gson().fromJson(data, type);
        if (charityEvent == null && id == DEFAULT_VALUE) {
            getMvpView().showLoadingError();
        } else {
            Event event = getMvpView().getEvent(charityEvent, id);
            getMvpView().showEventDetail(event);
        }
    }

}
