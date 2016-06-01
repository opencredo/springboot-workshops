package com.fictophone.services.records.mappers;

import com.fictophone.services.records.domain.CallRecord;
import com.fictophone.services.records.domain.Leg;
import com.fictophone.services.records.views.CallRecordView;
import com.fictophone.services.records.views.LegView;

import java.util.function.Function;

public final class CallRecordMappers {

    public static final Function<Leg, LegView> leg2View = leg ->
            new LegView(leg.getNumber(), leg.getStart(), leg.getEnd());

    public static final Function<CallRecord, CallRecordView> callRecord2View = record ->
            new CallRecordView(
                    record.getId(),
                    record.getOwnerId(),
                    leg2View.apply(record.getInbound()),
                    record.getOutbound().map(leg2View),
                    record.getOutcome()
            );

    public static final Function<LegView, Leg> view2Leg = view ->
            new Leg(view.getNumber(), view.getStart(), view.getEnd());

    public static final Function<CallRecordView, CallRecord> view2Record = view ->
            new CallRecord(
                view.getId(),
                view.getOwnerId(),
                view2Leg.apply(view.getInbound()),
                view.getOutbound().map(view2Leg),
                view.getOutcome());

}
