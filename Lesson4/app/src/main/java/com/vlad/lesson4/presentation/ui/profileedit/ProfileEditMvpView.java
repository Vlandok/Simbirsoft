package com.vlad.lesson4.presentation.ui.profileedit;

import com.arellomobile.mvp.MvpView;
import com.vlad.lesson4.data.model.Friend;
import com.vlad.lesson4.data.model.User;

import java.util.ArrayList;

public interface ProfileEditMvpView extends MvpView {
    void clickOnImageUser();

    void showInfoUser(User user, ArrayList<Friend> arrayListFriends);

    void showLoadingError();

    void showProgressView();

    void clickToExitAccount();

    void setImageUser(String urlImage);
}
