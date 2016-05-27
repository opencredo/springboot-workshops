package com.fictophone.services.numbers.domain;

import java.util.UUID;

public final class UserId extends SystemId {

    public static UserId of(UUID id) {
        return new UserId(id);
    }

    private UserId(UUID id) {
        super(id);
    }
}
