package com.vlad.lesson4.presentation.ui.profileedit;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Friend;
import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Objects;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileEditPresenter extends BasePresenter<ProfileEditMvpView> {

    private final static String USERS_REF = "users";

    private ArrayList<Friend> arrayListFriends;
    private Disposable disposable;

    public void onCreate() {
        checkViewAttached();
        getUserInfo();
    }

    @Override
    protected void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void getUserInfo() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference query = FirebaseDatabase.getInstance().getReference().child(USERS_REF)
                .child(Objects.requireNonNull(firebaseUser).getUid());
        disposable = RxFirebaseDatabase.observeSingleValueEvent(query, User.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getMvpView().showProgressView())
                .doOnError(Throwable::printStackTrace)
                .subscribe(user -> {
                    ArrayList<Friend> arrayListFriendsExample = initArrayListFriends();
                    if (arrayListFriendsExample.isEmpty()) {
                        getMvpView().showLoadingError();
                    } else {
                        getMvpView().showInfoUser(user, initArrayListFriends());
                        getMvpView().clickOnImageUser();
                        getMvpView().clickToExitAccount();
                    }
                });
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
