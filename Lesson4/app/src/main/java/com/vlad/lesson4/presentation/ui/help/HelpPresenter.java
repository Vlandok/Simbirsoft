package com.vlad.lesson4.presentation.ui.help;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.vlad.lesson4.domain.provider.CategoryProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class HelpPresenter extends BasePresenter<HelpMvpView> {

    private CategoryProvider categoryProvider;
    private ItemsJsonProvider itemsJsonProvider;

    @Inject
    public HelpPresenter(CategoryProvider categoryProvider,
                         ItemsJsonProvider itemsJsonProvider) {
        this.categoryProvider = categoryProvider;
        this.itemsJsonProvider = itemsJsonProvider;
    }

    @Override
    public void attachView(HelpMvpView view) {
        super.attachView(view);
        getItemsCategory();
    }

    @SuppressLint("CheckResult")
    void getItemsCategory() {
        categoryProvider.getCategories()
                .compose(applyBinding())
                .compose(categoryProvider.applyScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getViewState().showProgressView())
                .onErrorReturn(__ -> itemsJsonProvider.getListCategoriesFromJson())
                .doOnError(Throwable::printStackTrace)
                .subscribe(categories -> {
                    getViewState().showItemsCategory(categories);
                    getViewState().onClickCategory();
                });
    }
}


