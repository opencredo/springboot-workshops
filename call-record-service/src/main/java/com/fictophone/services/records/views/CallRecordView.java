package com.fictophone.services.records.views;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;
import java.util.UUID;

public class CallRecordView {

    @JsonProperty
    private final UUID id;

    @JsonProperty
    private final UUID ownerId;

    @JsonProperty
    private final LegView inbound;

    @JsonProperty
    private final Optional<LegView> outbound;

    @JsonProperty
    private final String outcome;

    @JsonCreator
    public CallRecordView(
            @JsonProperty("id") UUID id,
            @JsonProperty("ownerId") UUID ownerId,
            @JsonProperty("inbound") LegView inbound,
            @JsonProperty("outbound") Optional<LegView> outbound,
            @JsonProperty("outcome") String outcome) {
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

    public LegView getInbound() {
        return inbound;
    }

    public Optional<LegView> getOutbound() {
        return outbound;
    }

    public String getOutcome() {
        return outcome;
    }
}
