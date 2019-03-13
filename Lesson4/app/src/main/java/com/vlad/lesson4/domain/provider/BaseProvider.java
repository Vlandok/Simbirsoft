package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.remote.api.FirebaseApi;

import io.reactivex.MaybeTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

public class BaseProvider {
    protected final FirebaseApi firebaseApi;

    BaseProvider(FirebaseApi firebaseApi) {
        this.firebaseApi = firebaseApi;
    }

    public <T> SingleTransformer<T, T> applyScheduler() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io());
    }

    public <T> MaybeTransformer<T, T> applySchedulerMaybe() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io());
    }
}
