package com.vlad.lesson4.presentation.ui.profileedit;

import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

public class ProfileEditPresenter extends BasePresenter<ProfileEditMvpView> {

    public void onCreate() {
        checkViewAttached();
        getUserInfo();
    }

    @Override
    protected void doUnsubscribe() {

    }

    private void getUserInfo() {
        checkViewAttached();
        getMvpView().showProgressView();
        User user = initUserInfo();
        if (user == null) {
            getMvpView().showLoadingError();
        } else {
            getMvpView().showInfoUser(user);
            getMvpView().clickOnImageUser();
        }

    }

    private User initUserInfo() {
        return new User("Денис", "Константинов", 1, "Февраля",
                1980, "Хирургия. травматология", true);
    }
}
