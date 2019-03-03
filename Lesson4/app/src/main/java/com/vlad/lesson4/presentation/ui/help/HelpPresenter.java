package com.vlad.lesson4.presentation.ui.help;

import com.arellomobile.mvp.InjectViewState;
import com.vlad.lesson4.domain.provider.CategoryProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenterMoxy;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class HelpPresenter extends BasePresenterMoxy<HelpMvpView> {

    private Disposable disposable;
    @NonNull
    private CategoryProvider categoryProvider;
    @NonNull
    private ItemsJsonProvider itemsJsonProvider;

    public HelpPresenter(@NonNull CategoryProvider categoryProvider,
                         @NonNull ItemsJsonProvider itemsJsonProvider) {
        this.categoryProvider = categoryProvider;
        this.itemsJsonProvider = itemsJsonProvider;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getItemsCategory();

    }

    public void getItemsCategory() {
        disposable = categoryProvider.getCategories()
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


