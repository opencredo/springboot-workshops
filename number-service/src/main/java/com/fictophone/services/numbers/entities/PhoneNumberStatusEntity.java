package com.fictophone.services.numbers.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Entity(name="PhoneNumberStatus")
public class PhoneNumberStatusEntity {

    public static PhoneNumberStatusEntity of(String countryCode, String number, Instant timestamp, Optional<UUID> ownerId, Optional<String> configuration) {
        return new PhoneNumberStatusEntity(
                PhoneNumberStatusKey.of(countryCode, number, timestamp),
                ownerId.orElse(null),
                configuration.orElse(null));
    }

    @Id
    @Embedded
    private PhoneNumberStatusKey key;

    @Column(nullable = true)
    private UUID ownerId;

    @Column(nullable = true)
    private String configuration;

    protected PhoneNumberStatusEntity() { }

    private PhoneNumberStatusEntity(PhoneNumberStatusKey key, UUID ownerId, String configuration) {
        this.key = key;
        this.ownerId = ownerId;
        this.configuration = configuration;
    }

    public String getCountryCode() {
        return key.getCountryCode();
    }
    public String getNumber() {
        return key.getNumber();
    }
    public Instant getTimestamp() {
        return key.getTimestamp();
    }

    public Optional<UUID> getOwnerId() {
        return Optional.ofNullable(ownerId);
    }

    public Optional<String> getConfiguration() {
        return Optional.ofNullable(configuration);
    }

    @Override
    public String toString() {
        return "PhoneNumberStatusEntity{" +
                "key=" + key +
                ", ownerId=" + ownerId +
                ", configuration='" + configuration + '\'' +
                '}';
    }
}
