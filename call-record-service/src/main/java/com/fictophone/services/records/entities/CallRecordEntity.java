package com.fictophone.services.records.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Entity
public class CallRecordEntity {

    public static CallRecordEntity of(UUID id, UUID ownerId, String inboundNumber, Instant inboundStart, Instant inboundEnd,
                                      Optional<String> outboundNumber, Optional<Instant> outboundStart, Optional<Instant> outboundEnd,
                                      String outcome) {
        return new CallRecordEntity(id, ownerId, inboundNumber, inboundStart, inboundEnd,
                outboundNumber.orElse(null), outboundStart.orElse(null), outboundEnd.orElse(null), outcome);
    }

    @Id
    private UUID id;
    private UUID ownerId;
    private String inboundNumber;
    private Instant inboundStart;
    private Instant inboundEnd;
    private String outboundNumber;
    private Instant outboundStart;
    private Instant outboundEnd;
    private String outcome;

    private CallRecordEntity(UUID id, UUID ownerId, String inboundNumber, Instant inboundStart, Instant inboundEnd, String outboundNumber, Instant outboundStart, Instant outboundEnd, String outcome) {
        this.id = id;
        this.ownerId = ownerId;
        this.inboundNumber = inboundNumber;
        this.inboundStart = inboundStart;
        this.inboundEnd = inboundEnd;
        this.outboundNumber = outboundNumber;
        this.outboundStart = outboundStart;
        this.outboundEnd = outboundEnd;
        this.outcome = outcome;
    }

    protected CallRecordEntity() { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getInboundNumber() {
        return inboundNumber;
    }

    public void setInboundNumber(String inboundNumber) {
        this.inboundNumber = inboundNumber;
    }

    public Instant getInboundStart() {
        return inboundStart;
    }

    public void setInboundStart(Instant inboundStart) {
        this.inboundStart = inboundStart;
    }

    public Instant getInboundEnd() {
        return inboundEnd;
    }

    public void setInboundEnd(Instant inboundEnd) {
        this.inboundEnd = inboundEnd;
    }

    public String getOutboundNumber() {
        return outboundNumber;
    }

    public void setOutboundNumber(String outboundNumber) {
        this.outboundNumber = outboundNumber;
    }

    public Instant getOutboundStart() {
        return outboundStart;
    }

    public void setOutboundStart(Instant outboundStart) {
        this.outboundStart = outboundStart;
    }

    public Instant getOutboundEnd() {
        return outboundEnd;
    }

    public void setOutboundEnd(Instant outboundEnd) {
        this.outboundEnd = outboundEnd;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}


