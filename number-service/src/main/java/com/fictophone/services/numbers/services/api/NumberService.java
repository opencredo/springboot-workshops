package com.fictophone.services.numbers.services.api;

import com.fictophone.services.numbers.domain.*;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface NumberService {

    @Transactional
    void assignNumber(Instant startInclusive, PhoneNumber number, UserId userId);

    @Transactional
    void deassignNumber(Instant startInclusive, PhoneNumber number);

    @Transactional
    void configureNumber(Instant startInclusive, PhoneNumber number, NumberConfiguration numberConfiguration);

    Optional<OwnerAndConfiguration> getOwnerAndConfiguration(Instant atTime, PhoneNumber number);
    List<PhoneNumberAndConfiguration> getNumbersCurrentlyOwnedBy(UserId userId);

}
