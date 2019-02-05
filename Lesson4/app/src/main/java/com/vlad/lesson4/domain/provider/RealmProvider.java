package com.vlad.lesson4.domain.provider;

import com.vlad.lesson4.data.model.db.repository.RealmService;

public class RealmProvider {
    private final RealmService realmService;

    public RealmProvider(RealmService realmService) {
        this.realmService = realmService;
    }
}
