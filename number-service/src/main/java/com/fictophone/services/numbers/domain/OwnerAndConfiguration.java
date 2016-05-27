package com.fictophone.services.numbers.domain;

import java.util.Objects;
import java.util.Optional;

public class OwnerAndConfiguration {

    public static OwnerAndConfiguration of(Optional<UserId> ownerId, NumberConfiguration configuration) {
        return new OwnerAndConfiguration(ownerId, configuration);
    }

    private final Optional<UserId> ownerId;
    private final NumberConfiguration configuration;

    private OwnerAndConfiguration(Optional<UserId> ownerId, NumberConfiguration configuration) {
        this.ownerId = ownerId;
        this.configuration = configuration;
    }

    public Optional<UserId> getOwnerId() {
        return ownerId;
    }

    public NumberConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, configuration);
    }

    @Override
    public boolean equals(Object o) {
        return o == this ||
                ((o instanceof OwnerAndConfiguration)
                    && ((OwnerAndConfiguration) o).ownerId.equals(ownerId)
                    && ((OwnerAndConfiguration) o).configuration.equals(configuration));
    }

    @Override
    public String toString() {
        return ownerId + ", " + configuration;
    }

}
