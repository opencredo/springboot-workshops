package com.fictophone.services.records.views;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class LegView {

    @JsonProperty
    private final String number;

    @JsonProperty
    private final Instant start;

    @JsonProperty
    private final Instant end;

    @JsonCreator
    public LegView(
            @JsonProperty("number") String number,
            @JsonProperty("start") Instant start,
            @JsonProperty("end") Instant end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }

    public String getNumber() {
        return number;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }
}
