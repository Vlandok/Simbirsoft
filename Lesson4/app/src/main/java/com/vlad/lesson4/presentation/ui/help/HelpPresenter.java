package com.vlad.lesson4.presentation.ui.help;

import android.content.Context;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.ItemForChooseCategoryHelp;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.ArrayList;

public class HelpPresenter extends BasePresenter<HelpMvpView> {

    private ArrayList<ItemForChooseCategoryHelp> listItems;

    public void onCreate(Context context) {
        checkViewAttached();
        getItemsCategory(context);
    }

    private void getItemsCategory(Context context) {
        checkViewAttached();
        getMvpView().showProgressView();
        ArrayList<ItemForChooseCategoryHelp> arrayListItemsCategory = initItemsCategory(context);
        if (arrayListItemsCategory == null) {
            getMvpView().showLoadingError();
        } else {
            getMvpView().showItemsCategory(arrayListItemsCategory);
            getMvpView().onClickCategory();
        }
    }

    private ArrayList<ItemForChooseCategoryHelp> initItemsCategory(Context context) {
        listItems = new ArrayList<>();

        listItems.add(new ItemForChooseCategoryHelp(R.drawable.child_category, context.getString(R.string.child_category)));
        listItems.add(new ItemForChooseCategoryHelp(R.drawable.adults_category, context.getString(R.string.adults_category)));
        listItems.add(new ItemForChooseCategoryHelp(R.drawable.seniors_category, context.getString(R.string.seniors_category)));
        listItems.add(new ItemForChooseCategoryHelp(R.drawable.animals_category, context.getString(R.string.animals_category)));
        listItems.add(new ItemForChooseCategoryHelp(R.drawable.events_category, context.getString(R.string.events_category)));

        return listItems;
    }

    @Override
    protected void doUnsubscribe() {

    }

}
