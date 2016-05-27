package com.fictophone.services.numbers.repositories;

import com.fictophone.services.numbers.entities.PhoneNumberStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneNumberStatusRepository extends JpaRepository<PhoneNumberStatusEntity, UUID> {

    @Query(value = "SELECT * FROM Phone_Number_Status WHERE ownerId = :ownerId ORDER BY country_code ASC, number ASC, timestamp ASC",
    nativeQuery = true)
    List<PhoneNumberStatusEntity> getByOwnerId(@Param("ownerId") UUID ownerId);

    @Query(value = "SELECT * FROM Phone_Number_Status WHERE country_code = :countryCode AND number = :number " +
            "AND timestamp <= :atTime ORDER BY timestamp DESC LIMIT 1",
    nativeQuery = true)
    Optional<PhoneNumberStatusEntity> getMostRecentByNumber(
            @Param("atTime") Instant atTime,
            @Param("countryCode") String countryCode,
            @Param("number") String number);

    @Query(value = "SELECT DISTINCT q.* FROM phone_number_status AS p " +
            "JOIN phone_number_status AS q ON p.country_code = q.country_code AND p.number = q.number " +
            "WHERE p.owner_id = :userId AND p.timestamp <= :atTime " +
            "ORDER BY q.country_code ASC, q.number ASC, q.timestamp DESC",
            nativeQuery = true)
    List<PhoneNumberStatusEntity> getStatusHistoryByOwner(
            @Param("atTime") Instant atTime,
            @Param("userId") UUID userId);

}
