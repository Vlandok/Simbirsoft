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
        HelpTask helpTask = new HelpTask(context, getMvpView(), this);
        helpTask.execute();
    }

    void showCategories(HelpMvpView mvpView) {
        if (categories == null) {
            mvpView.showLoadingError();
            mvpView.onClickErrorButton();
        } else {
            mvpView.showItemsCategory(categories.getCategories());
            mvpView.onClickCategory();
        }
    }

    void jsonToEventCategories(Context context) {
        String data = JsonSupport.loadJSONFromAsset(context, FILE_JSON);
        Type type = new TypeToken<EventCategories>() {
        }.getType();
        try {
            categories = new Gson().fromJson(data, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doUnsubscribe() {
    }
}


