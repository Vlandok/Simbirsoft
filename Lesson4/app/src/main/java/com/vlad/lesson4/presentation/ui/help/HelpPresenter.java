package com.vlad.lesson4.presentation.ui.help;

import com.vlad.lesson4.domain.provider.CategoryProvider;
import com.vlad.lesson4.domain.provider.ItemsJsonProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class HelpPresenter extends BasePresenter<HelpMvpView> {

    private Disposable disposable;
    @NonNull
    private CategoryProvider categoryProvider;
    @NonNull
    private ItemsJsonProvider itemsJsonProvider;

    @Inject
    public HelpPresenter(@NonNull CategoryProvider categoryProvider,
                         @NonNull ItemsJsonProvider itemsJsonProvider) {
        this.categoryProvider = categoryProvider;
        this.itemsJsonProvider = itemsJsonProvider;
    }

    public void onCreate() {
        checkViewAttached();
        getItemsCategory();
    }

    public void getItemsCategory() {
        disposable = categoryProvider.getCategories()
                .compose(applyBinding())
                .compose(categoryProvider.applyScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getMvpView().showProgressView())
                .onErrorReturn(__ -> itemsJsonProvider.getListCategoriesFromJson())
                .doOnError(Throwable::printStackTrace)
                .subscribe(categories -> {
                    getMvpView().showItemsCategory(categories);
                    getMvpView().onClickCategory();

                });
    }

    @Override
    protected void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}


