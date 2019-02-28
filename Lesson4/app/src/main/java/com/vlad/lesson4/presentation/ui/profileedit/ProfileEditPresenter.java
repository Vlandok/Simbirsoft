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

import javax.inject.Inject;

import butterknife.BindView;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.Completable;
import rx.Observable;

public class ProfileEditPresenter extends BasePresenter<ProfileEditMvpView> {

    private final static String USERS_REF = "users";
    private final static String SWITCH_NOTIFY_REF = "isPushNotifications";

    private ArrayList<Friend> arrayListFriends;
    private Disposable disposable;
    private ProfileEditModel profileEditModel;
    private Observable<Boolean> switchObservable;
    private FirebaseAuth mAuth;

    public ProfileEditPresenter(ProfileEditModel profileEditModel) {
        this.profileEditModel = profileEditModel;
    }

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

    private void setSwitchNotify(DatabaseReference databaseReference) {
        switchObservable = profileEditModel.changeSwitchNotify();
        switchObservable.subscribe(aBoolean -> databaseReference.child(SWITCH_NOTIFY_REF)
                .setValue(aBoolean));
    }

    private Completable getImageUrl(final String url) {
        return Completable.create(subscriber -> {
            getMvpView().setImageUser(url);
            subscriber.onCompleted();
        });
    }

    private void getUserInfo() {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference query = FirebaseDatabase.getInstance().getReference().child(USERS_REF)
                    .child(Objects.requireNonNull(firebaseUser).getUid());
            disposable = RxFirebaseDatabase.observeSingleValueEvent(query, User.class)
                    .compose(applyBindingMaybe())
                    .compose(profileEditModel.applySchedulerMaybe())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(__ -> getMvpView().showProgressView())
                    .doOnError(Throwable::printStackTrace)
                    .subscribe(user -> getImageUrl(user.getFullImageUrl()).subscribe(() -> {
                        setSwitchNotify(query);
                        ArrayList<Friend> arrayListFriendsExample = initArrayListFriends();
                        if (arrayListFriendsExample.isEmpty()) {
                            getMvpView().showLoadingError();
                        } else {
                            getMvpView().showInfoUser(user, initArrayListFriends());
                            getMvpView().clickOnImageUser();
                            getMvpView().clickToExitAccount();
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
