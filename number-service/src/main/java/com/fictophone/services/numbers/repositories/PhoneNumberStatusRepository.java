package com.fictophone.services.numbers.repositories;

import com.fictophone.services.numbers.entities.PhoneNumberStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneNumberStatusRepository extends JpaRepository<PhoneNumberStatusEntity, UUID> {

    @Query(value = "SELECT * FROM Phone_Number_Status WHERE country_code = :countryCode AND number = :number " +
            "AND timestamp <= :atTime ORDER BY timestamp DESC LIMIT 1",
    nativeQuery = true)
    Optional<PhoneNumberStatusEntity> getMostRecentByNumber(
            @Param("atTime") Instant atTime,
            @Param("countryCode") String countryCode,
            @Param("number") String number);

}
