package com.vlad.lesson4.presentation.ui.help;

import android.content.Context;

import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.data.model.EventCategories;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class HelpPresenter extends BasePresenter<HelpMvpView> {

    private EventCategories categories = new EventCategories();
    private RealmList<Category> categoryRealmList = new RealmList<>();

    public void onCreate(Context context) {
        checkViewAttached();
        getItemsCategory(context);
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

    void getEventCategoriesFromRealm() {
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<Category> category = realm.where(Category.class).findAll();
            List<Category> categoryCopy = realm.copyFromRealm(category);
            if (categoryCopy != null) {
                categoryRealmList.addAll(categoryCopy.subList(0, categoryCopy.size()));
                categories.setCategories(categoryRealmList);
            } else {
                categories = null;
            }
        }
    }

    @Override
    protected void doUnsubscribe() {
    }
}


