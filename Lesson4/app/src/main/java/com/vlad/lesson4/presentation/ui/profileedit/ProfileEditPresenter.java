package com.vlad.lesson4.presentation.ui.profileedit;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Friend;
import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.domain.provider.ProfileEditProvider;
import com.vlad.lesson4.presentation.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rx.Completable;
import rx.Observable;

@InjectViewState
public class ProfileEditPresenter extends BasePresenter<ProfileEditMvpView> {

    private final static String USERS_REF = "users";
    private final static String SWITCH_NOTIFY_REF = "isPushNotifications";

    private ArrayList<Friend> arrayListFriends;
    private Observable<Boolean> switchObservable;
    private ProfileEditProvider profileEditProvider;
    private FirebaseAuth mAuth;

    @Inject
    public ProfileEditPresenter(ProfileEditProvider profileEditProvider) {
        this.profileEditProvider = profileEditProvider;
    }

    @Override
    public void attachView(ProfileEditMvpView view) {
        super.attachView(view);
        getUserInfo();
    }

    void initObservableSwitch(Observable<Boolean> observable) {
        switchObservable = observable;
    }

    private void setSwitchNotify(DatabaseReference databaseReference) {
        switchObservable.subscribe(aBoolean -> databaseReference.child(SWITCH_NOTIFY_REF)
                .setValue(aBoolean));
    }

    private Completable getImageUrl(final String url) {
        return Completable.create(subscriber -> {
            getViewState().setImageUser(url);
            subscriber.onCompleted();
        });
    }

    @SuppressLint("CheckResult")
    private void getUserInfo() {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference query = FirebaseDatabase.getInstance().getReference().child(USERS_REF)
                    .child(Objects.requireNonNull(firebaseUser).getUid());
            RxFirebaseDatabase.observeSingleValueEvent(query, User.class)
                    .compose(applyBindingMaybe())
                    .compose(profileEditProvider.applySchedulerMaybe())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(__ -> getViewState().showProgressView())
                    .doOnError(Throwable::printStackTrace)
                    .subscribe(user -> getImageUrl(user.getFullImageUrl()).subscribe(() -> {
                        setSwitchNotify(query);
                        ArrayList<Friend> arrayListFriendsExample = initArrayListFriends();
                        if (arrayListFriendsExample.isEmpty()) {
                            getViewState().showLoadingError();
                        } else {
                            getViewState().showInfoUser(user, initArrayListFriends());
                            getViewState().clickOnImageUser();
                            getViewState().clickToExitAccount();
                        }
                    }));
        }
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
