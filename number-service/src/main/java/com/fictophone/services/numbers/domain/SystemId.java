package com.fictophone.services.numbers.domain;

import java.util.UUID;

public abstract class SystemId {

    private final UUID id;

    protected SystemId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                (getClass().isInstance(o)
                    && ((SystemId) o).id.equals(id));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + id + ")";
    }
}
