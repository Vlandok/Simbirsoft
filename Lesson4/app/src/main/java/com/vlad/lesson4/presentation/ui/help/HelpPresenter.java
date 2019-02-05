package com.vlad.lesson4.presentation.ui.help;

import com.vlad.lesson4.data.model.EventCategories;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

public class HelpPresenter extends BasePresenter<HelpMvpView> {

    public void onCreate() {
        checkViewAttached();
        getItemsCategory();
    }

    private void getItemsCategory() {
        checkViewAttached();
        HelpTask helpTask = new HelpTask(getMvpView(), this);
        helpTask.execute();
    }

    void showCategories(HelpMvpView mvpView, EventCategories eventCategories) {
        if (eventCategories == null) {
            mvpView.showLoadingError();
            mvpView.onClickErrorButton();
        } else {
            mvpView.showItemsCategory(eventCategories.getCategories());
            mvpView.onClickCategory();
        }
    }

    @Override
    protected void doUnsubscribe() {
    }
}


