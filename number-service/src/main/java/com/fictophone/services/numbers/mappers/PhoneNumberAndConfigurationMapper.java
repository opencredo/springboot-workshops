package com.fictophone.services.numbers.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fictophone.services.numbers.domain.NumberConfiguration;
import com.fictophone.services.numbers.domain.PhoneNumberAndConfiguration;
import com.fictophone.services.numbers.views.PhoneNumberAndConfigurationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@Component
public final class PhoneNumberAndConfigurationMapper implements
        Function<PhoneNumberAndConfiguration, PhoneNumberAndConfigurationView> {

    private final ObjectMapper objectMapper;

    @Autowired
    public PhoneNumberAndConfigurationMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public PhoneNumberAndConfigurationView apply(PhoneNumberAndConfiguration phoneNumberAndConfiguration) {
        return PhoneNumberAndConfigurationView.of(
                phoneNumberAndConfiguration.getPhoneNumber().getCountryCode(),
                phoneNumberAndConfiguration.getPhoneNumber().getNumber(),
                configurationToJson(phoneNumberAndConfiguration.getConfiguration())
        );
    }

    private JsonNode configurationToJson(NumberConfiguration numberConfiguration) {
        try {
            return objectMapper.readTree(numberConfiguration.getConfiguration());
        } catch (IOException e) {
            throw new RuntimeJsonMappingException(e.getMessage());
        }
    }
}
