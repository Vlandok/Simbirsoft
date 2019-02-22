package com.vlad.lesson4.presentation.ui.profileedit;

import com.vlad.lesson4.data.model.Friend;
import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.presentation.ui.base.MvpView;

import java.util.ArrayList;

public interface ProfileEditMvpView extends MvpView {
    void clickOnImageUser();

    void showInfoUser(User user, ArrayList<Friend> arrayListFriends);

    void showLoadingError();

    void showProgressView();

    void clickToExitAccount();

    void setImageUser(String urlImage);
}
