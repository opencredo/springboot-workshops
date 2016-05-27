package com.fictophone.services.numbers.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PhoneNumberCurrentStatusKey implements Serializable {

    public static PhoneNumberCurrentStatusKey of(String countryCode, String number) {
        return new PhoneNumberCurrentStatusKey(countryCode, number);
    }

    @Column
    private String countryCode;

    @Column
    private String number;

    private PhoneNumberCurrentStatusKey(String countryCode, String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    protected PhoneNumberCurrentStatusKey() { }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, number);
    }

    @Override
    public boolean equals(Object o) {
        return o == this ||
                ((o instanceof PhoneNumberCurrentStatusKey)
                    && ((PhoneNumberCurrentStatusKey) o).countryCode.equals(countryCode)
                    && ((PhoneNumberCurrentStatusKey) o).number.equals(number));
    }
}
