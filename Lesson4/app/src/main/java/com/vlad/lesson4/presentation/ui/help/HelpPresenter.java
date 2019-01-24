package com.vlad.lesson4.presentation.ui.help;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vlad.lesson4.data.model.EventCategories;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;
import com.vlad.lesson4.utils.JsonSupport;

import java.lang.reflect.Type;

public class HelpPresenter extends BasePresenter<HelpMvpView> {

    private static final String FILE_JSON = "categories.json";

    private EventCategories categories;

    public void onCreate(Context context) {
        checkViewAttached();
        getItemsCategory(context);
    }

    private void getItemsCategory(Context context) {
        checkViewAttached();
        getMvpView().showProgressView();
        String data = JsonSupport.loadJSONFromAsset(context, FILE_JSON);
        Type type = new TypeToken<EventCategories>() {
        }.getType();
        try {
            categories = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        if (categories == null) {
            getMvpView().showLoadingError();
        } else {
            getMvpView().showItemsCategory(categories.getCategories());
            getMvpView().onClickCategory();
        }
    }

    @Override
    protected void doUnsubscribe() {

    }

}
