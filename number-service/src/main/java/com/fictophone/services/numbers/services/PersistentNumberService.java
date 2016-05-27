package com.fictophone.services.numbers.services;

import com.fictophone.services.numbers.domain.*;
import com.fictophone.services.numbers.entities.PhoneNumberCurrentStatusEntity;
import com.fictophone.services.numbers.entities.PhoneNumberStatusEntity;
import com.fictophone.services.numbers.repositories.PhoneNumberCurrentStatusRepository;
import com.fictophone.services.numbers.repositories.PhoneNumberStatusRepository;
import com.fictophone.services.numbers.services.api.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public final class PersistentNumberService implements NumberService {

    private final PhoneNumberStatusRepository statusRepository;
    private final PhoneNumberCurrentStatusRepository currentStatusRepository;

    @Autowired
    public PersistentNumberService(PhoneNumberStatusRepository statusRepository, PhoneNumberCurrentStatusRepository currentStatusRepository) {
        this.statusRepository = statusRepository;
        this.currentStatusRepository = currentStatusRepository;
    }

    private void saveHistoricalAndCurrent(Instant startInclusive, PhoneNumber number, Optional<UserId> owner, Optional<NumberConfiguration> configuration) {
        statusRepository.save(PhoneNumberStatusEntity.of(
                number.getCountryCode(),
                number.getNumber(),
                startInclusive,
                owner.map(UserId::getId),
                configuration.map(NumberConfiguration::getConfiguration)
        ));

        currentStatusRepository.save(PhoneNumberCurrentStatusEntity.of(
                number.getCountryCode(),
                number.getNumber(),
                owner.map(UserId::getId),
                configuration.map(NumberConfiguration::getConfiguration)
        ));
    }

    @Override
    public void assignNumber(Instant startInclusive, PhoneNumber number, UserId userId) {
        saveHistoricalAndCurrent(startInclusive, number, Optional.of(userId), Optional.empty());
    }

    @Override
    public void deassignNumber(Instant startInclusive, PhoneNumber number) {
        saveHistoricalAndCurrent(startInclusive, number, Optional.empty(), Optional.empty());
    }

    @Override
    public void configureNumber(Instant startInclusive, PhoneNumber number, NumberConfiguration numberConfiguration) {
        UserId owner = getOwnerAndConfiguration(startInclusive, number)
                .map(OwnerAndConfiguration::getOwnerId)
                .orElseThrow(() -> new IllegalStateException("Cannot configure number without an owner"));

        saveHistoricalAndCurrent(startInclusive, number, Optional.of(owner), Optional.of(numberConfiguration));
    }

    @Override
    public Optional<OwnerAndConfiguration> getOwnerAndConfiguration(Instant atTime, PhoneNumber number) {
        Optional<PhoneNumberStatusEntity> maybeStatus = statusRepository.getMostRecentByNumber(
                atTime,
                number.getCountryCode(),
                number.getNumber());

        return maybeStatus.flatMap(this::toOwnerAndConfiguration);
    }

    private Optional<OwnerAndConfiguration> toOwnerAndConfiguration(PhoneNumberStatusEntity status) {
        NumberConfiguration configuration = status.getConfiguration()
                .map(NumberConfiguration::of)
                .orElseGet(NumberConfiguration::empty);

        return status.getOwnerId().map(ownerId -> OwnerAndConfiguration.of(
                UserId.of(ownerId),
                configuration));
    }

    @Override
    public List<PhoneNumberAndConfiguration> getNumbersCurrentlyOwnedBy(UserId userId) {
        List<PhoneNumberCurrentStatusEntity> statuses = currentStatusRepository.findByOwnerId(userId.getId());

        return statuses.stream()
                .map(this::toPhoneNumberAndConfiguration)
                .collect(toList());
    }

    private PhoneNumberAndConfiguration toPhoneNumberAndConfiguration(PhoneNumberCurrentStatusEntity currentStatus) {
        return PhoneNumberAndConfiguration.of(
                PhoneNumber.of(
                        currentStatus.getCountryCode(),
                        currentStatus.getNumber()),
                currentStatus.getConfiguration().map(NumberConfiguration::of).orElseGet(NumberConfiguration::empty));
    }
}
