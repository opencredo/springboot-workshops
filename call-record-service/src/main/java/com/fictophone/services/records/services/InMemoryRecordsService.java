package com.fictophone.services.records.services;

import com.fictophone.services.records.domain.CallRecord;
import com.fictophone.services.records.services.api.RecordsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryRecordsService implements RecordsService {

    private final List<CallRecord> callRecords = new ArrayList<>();

    @Override
    public void addRecord(CallRecord callRecord) {
        callRecords.add(callRecord);
    }

    @Override
    public List<CallRecord> retrieveRecords() {
        return callRecords;
    }
}
