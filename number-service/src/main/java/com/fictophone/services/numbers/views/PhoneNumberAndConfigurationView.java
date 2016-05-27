package com.fictophone.services.numbers.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public final class PhoneNumberAndConfigurationView {

    public static PhoneNumberAndConfigurationView of(String companyCode, String number, JsonNode configuration) {
        return new PhoneNumberAndConfigurationView(companyCode, number, configuration);
    }

    @JsonProperty
    private final String companyCode;

    @JsonProperty
    private final String number;

    @JsonProperty
    private final JsonNode configuration;

    private PhoneNumberAndConfigurationView(String companyCode, String number, JsonNode configuration) {
        this.companyCode = companyCode;
        this.number = number;
        this.configuration = configuration;
    }

}
