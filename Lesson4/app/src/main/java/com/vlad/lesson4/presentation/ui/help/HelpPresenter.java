package com.vlad.lesson4.presentation.ui.help;

import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.domain.provider.CategoryProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HelpPresenter extends BasePresenter<HelpMvpView> {

    @Nullable
    private Disposable disposable;
    @NonNull
    private CategoryProvider categoryProvider;

    public HelpPresenter(@NonNull CategoryProvider categoryProvider) {
        this.categoryProvider = categoryProvider;
    }

    public void onCreate() {
        checkViewAttached();
        getItemsCategory();
    }

    public void getItemsCategory() {
        checkViewAttached();
        getMvpView().showProgressView();
        disposable = categoryProvider.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                            getMvpView().showItemsCategory(categories);
                            getMvpView().onClickCategory();
                        },
                        error -> {
                            checkViewAttached();
                            HelpTask helpTask = new HelpTask(getMvpView(), this);
                            helpTask.execute();
                            error.printStackTrace();
//                            getMvpView().showLoadingError();
//                            getMvpView().onClickErrorButton();
                        });
    }

    void showCategories(HelpMvpView mvpView, List<Category> categories) {
        if (categories.isEmpty()) {
            mvpView.showLoadingError();
            mvpView.onClickErrorButton();
        } else {
            mvpView.showItemsCategory(categories);
            mvpView.onClickCategory();
        }
    }

    @Override
    protected void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}


