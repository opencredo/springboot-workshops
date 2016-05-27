package com.fictophone.services.numbers.domain;

import java.util.Objects;

public class PhoneNumberAndConfiguration {

    public static PhoneNumberAndConfiguration of(PhoneNumber phoneNumber, NumberConfiguration configuration) {
        return new PhoneNumberAndConfiguration(phoneNumber, configuration);
    }

    private final PhoneNumber phoneNumber;
    private final NumberConfiguration configuration;

    private PhoneNumberAndConfiguration(PhoneNumber phoneNumber, NumberConfiguration configuration) {
        this.phoneNumber = phoneNumber;
        this.configuration = configuration;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public NumberConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, configuration);
    }

    @Override
    public boolean equals(Object o) {
        return o == this ||
                ((o instanceof PhoneNumberAndConfiguration)
                    && ((PhoneNumberAndConfiguration) o).phoneNumber.equals(phoneNumber)
                    && ((PhoneNumberAndConfiguration) o).configuration.equals(configuration));
    }

    @Override
    public String toString() {
        return phoneNumber + ", " + configuration;
    }

}
