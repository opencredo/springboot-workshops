package com.fictophone.services.numbers.repositories;

import com.fictophone.services.numbers.entities.PhoneNumberCurrentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhoneNumberCurrentStatusRepository extends JpaRepository<PhoneNumberCurrentStatusEntity, UUID> {

    List<PhoneNumberCurrentStatusEntity> findByOwnerId(UUID ownerId);

}
