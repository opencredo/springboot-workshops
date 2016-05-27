package com.fictophone.services.numbers.domain;

import java.util.UUID;

public final class TariffId extends SystemId {

    public static TariffId of(UUID id) {
        return new TariffId(id);
    }

    private TariffId(UUID id) {
        super(id);
    }
}
