package com.vlad.lesson4.presentation.ui.profileedit;


import io.reactivex.MaybeTransformer;
import rx.Observable;

public interface ProfileEditModel {
    Observable<Boolean> changeSwitchNotify();

    <T> MaybeTransformer<T, T> applySchedulerMaybe();
}
