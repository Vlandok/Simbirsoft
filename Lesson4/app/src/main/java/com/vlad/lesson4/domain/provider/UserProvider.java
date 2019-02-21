package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.data.remote.api.FirebaseApi;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

public class UserProvider {
    private final FirebaseApi firebaseApi;

    public UserProvider(FirebaseApi firebaseApi) {
        this.firebaseApi = firebaseApi;
    }

    public Single<List<User>> getUsers() {
        return firebaseApi.getUsers();
    }

    public <T> SingleTransformer<T, T> applyScheduler() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io());
    }
}
