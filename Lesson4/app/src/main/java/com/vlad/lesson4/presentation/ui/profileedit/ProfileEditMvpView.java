package com.vlad.lesson4.presentation.ui.profileedit;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Friend;
import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.presentation.ui.base.MvpView;

import java.util.ArrayList;

import butterknife.OnClick;

public interface ProfileEditMvpView extends MvpView {
    void clickOnImageUser();

    void showInfoUser(User user, ArrayList<Friend> arrayListFriends);

    void showLoadingError();

    void showProgressView();

    void clickToExitAccount();
}
