package com.fictophone.services.numbers.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public final class PhoneNumberAndConfigurationView {

    public static PhoneNumberAndConfigurationView of(String countryCode, String number, JsonNode configuration) {
        return new PhoneNumberAndConfigurationView(countryCode, number, configuration);
    }

    @JsonProperty
    private final String countryCode;

    @JsonProperty
    private final String number;

    @JsonProperty
    private final JsonNode configuration;

    private PhoneNumberAndConfigurationView(String countryCode, String number, JsonNode configuration) {
        this.countryCode = countryCode;
        this.number = number;
        this.configuration = configuration;
    }

}
