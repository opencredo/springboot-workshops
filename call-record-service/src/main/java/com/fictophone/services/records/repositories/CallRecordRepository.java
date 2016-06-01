package com.fictophone.services.records.repositories;

import com.fictophone.services.records.entities.CallRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallRecordRepository extends JpaRepository<CallRecordEntity, UUID> {
}
