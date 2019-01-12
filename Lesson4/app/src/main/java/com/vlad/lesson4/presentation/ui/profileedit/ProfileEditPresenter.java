package com.vlad.lesson4.presentation.ui.profileedit;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Friend;
import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.ArrayList;

public class ProfileEditPresenter extends BasePresenter<ProfileEditMvpView> {

    private ArrayList<Friend> arrayListFriends;

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
        ArrayList<Friend> arrayListFriendsExample = initArrayListFriends();
        if (user == null || arrayListFriendsExample == null) {
            getMvpView().showLoadingError();
        } else {
            getMvpView().showInfoUser(user, initArrayListFriends());
            getMvpView().clickOnImageUser();
        }

    }

    private User initUserInfo() {
        return new User("Денис", "Константинов", 1, "Февраля",
                1980, "Хирургия. травматология", true);
    }

    private ArrayList<Friend> initArrayListFriends() {
        arrayListFriends = new ArrayList<>();
        arrayListFriends.add(new Friend("Дмитрий", "Валерьевич", R.drawable.avatar_3));
        arrayListFriends.add(new Friend("Евгений", "Александров", R.drawable.avatar_2));
        arrayListFriends.add(new Friend("Виктор", "Кузнецов", R.drawable.avatar_1));
        arrayListFriends.add(new Friend("Дмитрий", "Валерьевич", R.drawable.avatar_3));
        arrayListFriends.add(new Friend("Евгений", "Александров", R.drawable.avatar_2));
        arrayListFriends.add(new Friend("Виктор", "Кузнецов", R.drawable.avatar_1));
        return arrayListFriends;
    }
}
