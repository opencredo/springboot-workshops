package com.fictophone.services.numbers.services;

import com.fictophone.services.numbers.Application;
import com.fictophone.services.numbers.domain.OwnerAndConfiguration;
import com.fictophone.services.numbers.domain.PhoneNumber;
import com.fictophone.services.numbers.domain.PhoneNumberAndConfiguration;
import com.fictophone.services.numbers.domain.UserId;
import com.fictophone.services.numbers.services.api.NumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PersistentNumberServiceIntegrationTest {

    @Autowired
    private NumberService numberService;

    @Test
    public void assignsNumberToUser() {
        UserId userId = UserId.of(UUID.randomUUID());
        PhoneNumber number = PhoneNumber.of("44", "1604123456");
        Instant timestamp = Instant.now();

        numberService.assignNumber(timestamp, number, userId);

        assertThat(
                numberService.getOwnerAndConfiguration(timestamp, number)
                        .map(OwnerAndConfiguration::getOwnerId),
                equalTo(Optional.of(userId)));
    }

    @Test
    public void deassignAndReassignNumber() {
        UserId userId1 = UserId.of(UUID.randomUUID());
        UserId userId2 = UserId.of(UUID.randomUUID());
        PhoneNumber number = PhoneNumber.of("44", "1604123456");
        Instant timestamp = Instant.now();

        numberService.assignNumber(timestamp.minus(1, DAYS), number, userId1);
        numberService.deassignNumber(timestamp, number);
        numberService.assignNumber(timestamp.plus(1, DAYS), number, userId2);

        // Still assigned in the past
        assertThat(
                ownerOf(number, timestamp.minus(1, DAYS)),
                equalTo(Optional.of(userId1)));

        // No longer assigned in the present
        assertThat(
                ownerOf(number, timestamp),
                equalTo(Optional.empty()));

        // Assigned to new user in the future
        assertThat(
                ownerOf(number, timestamp.plus(1, DAYS)),
                equalTo(Optional.of(userId2)));
    }

    private Optional<UserId> ownerOf(PhoneNumber number, Instant timestamp) {
        return numberService.getOwnerAndConfiguration(timestamp, number)
                .flatMap(OwnerAndConfiguration::getOwnerId);
    }

    @Test
    public void retrieveAllNumbersAssignedToUser() {
        UserId userId1 = UserId.of(UUID.randomUUID());
        UserId userId2 = UserId.of(UUID.randomUUID());

        PhoneNumber number1 = PhoneNumber.of("44", "1604123456");
        PhoneNumber number2 = PhoneNumber.of("44", "7954123456");
        PhoneNumber number3 = PhoneNumber.of("44", "898505050");
        PhoneNumber number4 = PhoneNumber.of("44", "1908123456");

        Instant timestamp = Instant.now();

        // Number 3 was assigned to user 2 in the distant past
        numberService.assignNumber(timestamp.minus(2, DAYS), number2, userId2);

        // All numbers assigned to user 1 in the past
        numberService.assignNumber(timestamp.minus(1, DAYS), number1, userId1);
        numberService.assignNumber(timestamp.minus(1, DAYS), number2, userId1);
        numberService.assignNumber(timestamp.minus(1, DAYS), number3, userId1);
        numberService.assignNumber(timestamp.minus(1, DAYS), number4, userId1);

        // Number 2 now reassigned to user 2
        numberService.assignNumber(timestamp, number2, userId2);

        // Number 4 now deassigned
        numberService.deassignNumber(timestamp, number4);

        assertThat(numbersOwnedBy(userId1),
            containsInAnyOrder(number1, number3));


        assertThat(numbersOwnedBy(userId2),
                containsInAnyOrder(number2));
    }

    private List<PhoneNumber> numbersOwnedBy(UserId userId) {
        return numberService.getNumbersCurrentlyOwnedBy(userId).stream()
                        .map(PhoneNumberAndConfiguration::getPhoneNumber)
                        .collect(toList());
    }

}
