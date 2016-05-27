package com.fictophone.services.numbers.rest;

import com.fictophone.services.numbers.domain.PhoneNumber;
import com.fictophone.services.numbers.domain.UserId;
import com.fictophone.services.numbers.mappings.PhoneNumberAndConfigurationMapper;
import com.fictophone.services.numbers.services.api.NumberService;
import com.fictophone.services.numbers.views.PhoneNumberAndConfigurationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/numbers")
public class NumbersController {

    private final PhoneNumberAndConfigurationMapper phoneNumberAndConfigurationMapper;
    private final NumberService numberService;

    @Autowired
    public NumbersController(PhoneNumberAndConfigurationMapper phoneNumberAndConfigurationMapper, NumberService numberService) {
        this.phoneNumberAndConfigurationMapper = phoneNumberAndConfigurationMapper;
        this.numberService = numberService;
    }

    @RequestMapping(value = "/numbers/{countryCode}/{number}/owner", method = RequestMethod.PUT)
    public List<PhoneNumberAndConfigurationView> assignNumber(
            @PathVariable("countryCode") String countryCode,
            @PathVariable("number") String number,
            @RequestBody UUID userId) {
        numberService.assignNumber(Instant.now(), PhoneNumber.of(countryCode, number), UserId.of(userId));

        return getNumbersCurrentlyOwned(userId);
    }

    @RequestMapping(value = "/owners/{userId}/numbers", method = RequestMethod.GET)
    public List<PhoneNumberAndConfigurationView> getNumbersCurrentlyOwned(@PathVariable("userId") UUID userId) {
        return numberService.getNumbersCurrentlyOwnedBy(UserId.of(userId)).stream()
                .map(phoneNumberAndConfigurationMapper)
                .collect(Collectors.toList());
    }
}
