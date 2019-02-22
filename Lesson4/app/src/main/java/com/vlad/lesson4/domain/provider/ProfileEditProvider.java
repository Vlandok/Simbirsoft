package com.vlad.lesson4.domain.provider;

import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditModel;
import com.vlad.lesson4.presentation.ui.profileedit.ProfileEditViewHolderRx;

import rx.Observable;

public class ProfileEditProvider implements ProfileEditModel {
    private final ProfileEditViewHolderRx profileEditViewHolderRx;

    public ProfileEditProvider(ProfileEditViewHolderRx profileEditViewHolder) {
        this.profileEditViewHolderRx = profileEditViewHolder;
    }

    @Override
    public Observable<Boolean> changeSwitchNotify() {
        return RxCompoundButton.checkedChanges(profileEditViewHolderRx.switchNotify);
    }
}
