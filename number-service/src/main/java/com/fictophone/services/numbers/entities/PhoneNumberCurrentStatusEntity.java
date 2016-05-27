package com.fictophone.services.numbers.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;
import java.util.UUID;

@Entity(name="PhoneNumberCurrentStatus")
public class PhoneNumberCurrentStatusEntity {

    public static PhoneNumberCurrentStatusEntity of(String countryCode, String number, Optional<UUID> ownerId, Optional<String> configuration) {
        return new PhoneNumberCurrentStatusEntity(
                PhoneNumberCurrentStatusKey.of(countryCode, number),
                ownerId.orElse(null),
                configuration.orElse(null));
    }

    @Id
    @Embedded
    private PhoneNumberCurrentStatusKey key;

    @Column(nullable = true)
    private UUID ownerId;

    @Column(nullable = true)
    private String configuration;

    protected PhoneNumberCurrentStatusEntity() { }

    private PhoneNumberCurrentStatusEntity(PhoneNumberCurrentStatusKey key, UUID ownerId, String configuration) {
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

    public Optional<UUID> getOwnerId() {
        return Optional.ofNullable(ownerId);
    }

    public Optional<String> getConfiguration() {
        return Optional.ofNullable(configuration);
    }

}
