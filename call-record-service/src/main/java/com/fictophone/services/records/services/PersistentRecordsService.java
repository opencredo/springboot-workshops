package com.fictophone.services.records.services;

import com.fictophone.services.records.domain.CallRecord;
import com.fictophone.services.records.mappers.CallRecordEntityMapper;
import com.fictophone.services.records.repositories.CallRecordRepository;
import com.fictophone.services.records.services.api.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Profile("!test")
public class PersistentRecordsService implements RecordsService {

    private final CallRecordRepository repository;
    private final CallRecordEntityMapper mapper;

    @Autowired
    public PersistentRecordsService(CallRecordRepository repository, CallRecordEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void addRecord(CallRecord callRecord) {
        repository.save(mapper.mapRecord(callRecord));
    }

    @Override
    public List<CallRecord> retrieveRecords() {
        return repository.findAll().stream().map(mapper::mapRecordEntity).collect(toList());
    }
}
