package com.vlad.lesson4.presentation.ui.profileedit;

import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.presentation.ui.base.MvpView;

public interface ProfileEditMvpView extends MvpView {
    void clickOnImageUser();

    void showInfoUser(User user);

    void showLoadingError();

    void showProgressView();
}
