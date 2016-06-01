package com.fictophone.services.records.mappers;

import com.fictophone.services.records.domain.CallRecord;
import com.fictophone.services.records.domain.Leg;
import com.fictophone.services.records.entities.CallRecordEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CallRecordEntityMapper {

    public CallRecordEntity mapRecord(CallRecord callRecord) {
        return CallRecordEntity.of(
                callRecord.getId(),
                callRecord.getOwnerId(),
                callRecord.getInbound().getNumber(),
                callRecord.getInbound().getStart(),
                callRecord.getInbound().getEnd(),
                callRecord.getOutbound().map(Leg::getNumber),
                callRecord.getOutbound().map(Leg::getStart),
                callRecord.getOutbound().map(Leg::getEnd),
                callRecord.getOutcome()
        );
    }

    public CallRecord mapRecordEntity(CallRecordEntity entity) {
        return new CallRecord(
                entity.getId(),
                entity.getOwnerId(),
                mapInboundLeg(entity),
                mapOutboundLeg(entity),
                entity.getOutcome()
        );
    }

    private Optional<Leg> mapOutboundLeg(CallRecordEntity entity) {
        return Optional.ofNullable(entity.getOutboundNumber())
                .map(outboundNumber ->
                        new Leg(
                                outboundNumber,
                                entity.getOutboundStart(),
                                entity.getOutboundEnd()));
    }

    private Leg mapInboundLeg(CallRecordEntity entity) {
        return new Leg(
                entity.getInboundNumber(),
                entity.getInboundStart(),
                entity.getInboundEnd());
    }
}
