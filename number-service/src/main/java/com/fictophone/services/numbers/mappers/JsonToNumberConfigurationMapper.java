package com.fictophone.services.numbers.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fictophone.services.numbers.domain.NumberConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class JsonToNumberConfigurationMapper implements Function<JsonNode, NumberConfiguration> {

    private final ObjectMapper mapper;

    @Autowired
    public JsonToNumberConfigurationMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public NumberConfiguration apply(JsonNode jsonNode) {
        return NumberConfiguration.of(nodeToString(jsonNode));
    }

    private String nodeToString(JsonNode jsonNode) {
        try {
            return mapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeJsonMappingException(e.getMessage());
        }
    }
}
