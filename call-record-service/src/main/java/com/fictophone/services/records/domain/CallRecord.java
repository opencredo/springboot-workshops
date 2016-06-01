package com.fictophone.services.records.domain;

import java.util.Optional;
import java.util.UUID;

public class CallRecord {

    private final UUID id;
    private final UUID ownerId;
    private final Leg inbound;
    private final Optional<Leg> outbound;
    private final String outcome;

    public CallRecord(
            UUID id,
            UUID ownerId,
            Leg inbound,
            Optional<Leg> outbound,
            String outcome) {
        this.id = id;
        this.ownerId = ownerId;
        this.inbound = inbound;
        this.outbound = outbound;
        this.outcome = outcome;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public Leg getInbound() {
        return inbound;
    }

    public Optional<Leg> getOutbound() {
        return outbound;
    }

    public String getOutcome() {
        return outcome;
    }
}
