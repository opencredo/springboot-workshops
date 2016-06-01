package com.fictophone.services.records.mappers;

import com.fictophone.services.records.domain.CallRecord;
import com.fictophone.services.records.domain.Leg;
import com.fictophone.services.records.views.CallRecordView;
import com.fictophone.services.records.views.LegView;
import org.springframework.stereotype.Component;

@Component
public final class CallRecordMapper {

    private LegView convertLeg(Leg leg) {
        return new LegView(leg.getNumber(), leg.getStart(), leg.getEnd());
    }

    public CallRecordView convertRecord(CallRecord record) {
        return new CallRecordView(
                record.getId(),
                record.getOwnerId(),
                convertLeg(record.getInbound()),
                record.getOutbound().map(this::convertLeg),
                record.getOutcome()
        );
    }

    private Leg convertLegView(LegView view) {
        return new Leg(view.getNumber(), view.getStart(), view.getEnd());
    }

    public CallRecord convertRecordView(CallRecordView view) {
        return new CallRecord(
                view.getId(),
                view.getOwnerId(),
                convertLegView(view.getInbound()),
                view.getOutbound().map(this::convertLegView),
                view.getOutcome());
    }

}
