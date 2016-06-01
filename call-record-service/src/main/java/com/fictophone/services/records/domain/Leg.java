package com.fictophone.services.records.domain;

import java.time.Instant;

public class Leg {

    private final String number;
    private final Instant start;
    private final Instant end;

    public Leg(
            String number,
            Instant start,
            Instant end) {
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
