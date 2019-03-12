package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.remote.api.FirebaseApi;

import io.reactivex.MaybeTransformer;
import io.reactivex.schedulers.Schedulers;

public class ProfileEditProvider {
    private FirebaseApi firebaseApi;

    public ProfileEditProvider(FirebaseApi firebaseApi) {
        this.firebaseApi = firebaseApi;
    }

    public <T> MaybeTransformer<T, T> applySchedulerMaybe() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io());
    }
}
