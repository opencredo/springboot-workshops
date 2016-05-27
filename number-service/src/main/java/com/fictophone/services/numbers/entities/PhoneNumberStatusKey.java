package com.fictophone.services.numbers.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class PhoneNumberStatusKey implements Serializable {

    public static PhoneNumberStatusKey of(String countryCode, String number, Instant timestamp) {
        return new PhoneNumberStatusKey(countryCode, number, timestamp);
    }

    @Column
    private String countryCode;

    @Column
    private String number;

    @Column
    private Instant timestamp;

    private PhoneNumberStatusKey(String countryCode, String number, Instant timestamp) {
        this.countryCode = countryCode;
        this.number = number;
        this.timestamp = timestamp;
    }

    protected PhoneNumberStatusKey() { }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, number, timestamp);
    }

    @Override
    public boolean equals(Object o) {
        return o == this ||
                ((o instanceof PhoneNumberStatusKey)
                    && ((PhoneNumberStatusKey) o).countryCode.equals(countryCode)
                    && ((PhoneNumberStatusKey) o).number.equals(number)
                    && ((PhoneNumberStatusKey) o).timestamp.equals(timestamp));
    }
}
