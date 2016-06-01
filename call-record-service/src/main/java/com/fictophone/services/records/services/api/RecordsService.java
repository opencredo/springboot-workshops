package com.fictophone.services.records.services.api;

import com.fictophone.services.records.domain.CallRecord;

import java.util.List;

public interface RecordsService {

    void addRecord(CallRecord callRecord);
    List<CallRecord> retrieveRecords();

}
