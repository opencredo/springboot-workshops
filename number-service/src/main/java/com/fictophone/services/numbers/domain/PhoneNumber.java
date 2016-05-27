package com.fictophone.services.numbers.domain;

import java.util.Objects;

public final class PhoneNumber {

    public static PhoneNumber of(String countryCode, String number) {
        return new PhoneNumber(countryCode, number);
    }

    private final String countryCode;
    private final String number;

    private PhoneNumber(String countryCode, String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String display() {
        return countryCode + number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, number);
    }

    @Override
    public boolean equals(Object o) {
        return o == this ||
                ((o instanceof PhoneNumber)
                        && ((PhoneNumber) o).countryCode.equals(countryCode)
                        && ((PhoneNumber) o).number.equals(number));
    }

    @Override
    public String toString() {
        return "#" + display();
    }


}
