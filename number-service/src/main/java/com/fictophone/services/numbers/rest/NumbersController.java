package com.fictophone.services.numbers.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fictophone.services.numbers.domain.PhoneNumber;
import com.fictophone.services.numbers.domain.UserId;
import com.fictophone.services.numbers.mappers.JsonToNumberConfigurationMapper;
import com.fictophone.services.numbers.mappers.PhoneNumberAndConfigurationMapper;
import com.fictophone.services.numbers.services.api.NumberService;
import com.fictophone.services.numbers.views.PhoneNumberAndConfigurationView;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/numbers")
public class NumbersController {

    private final JsonToNumberConfigurationMapper numberConfigurationMapper;
    private final PhoneNumberAndConfigurationMapper phoneNumberAndConfigurationMapper;
    private final NumberService numberService;

    @Autowired
    public NumbersController(
            JsonToNumberConfigurationMapper numberConfigurationMapper,
            PhoneNumberAndConfigurationMapper phoneNumberAndConfigurationMapper,
            NumberService numberService) {
        this.numberConfigurationMapper = numberConfigurationMapper;
        this.phoneNumberAndConfigurationMapper = phoneNumberAndConfigurationMapper;
        this.numberService = numberService;
    }

    @ApiOperation("Assign a number to a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Number successfully assigned to user"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/{countryCode}/{number}/owner", method = RequestMethod.PUT)
    public void assignNumber(
            @ApiParam("The country code of the number, e.g. +44") @PathVariable("countryCode") String countryCode,
            @ApiParam("The rest of the number, e.g. 8989505050") @PathVariable("number") String number,
            @ApiParam("The id of the user to assign the number to") @RequestBody UUID userId) {
        numberService.assignNumber(Instant.now(), PhoneNumber.of(countryCode, number), UserId.of(userId));
    }

    @ApiOperation("Configure a number's behaviour")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Number successfully configured"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/{countryCode}/{number}/configuration", method = RequestMethod.PUT)
    public void configureNumber(
            @ApiParam("The country code of the number, e.g. +44") @PathVariable("countryCode") String countryCode,
            @ApiParam("The rest of the number, e.g. 8989505050") @PathVariable("number") String number,
            @ApiParam("The configuration of the number, in JSON format") @RequestBody JsonNode configuration) {
        numberService.configureNumber(
                Instant.now(),
                PhoneNumber.of(countryCode, number),
                numberConfigurationMapper.apply(configuration));
    }

    @ApiOperation("De-assign a number")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Number successfully de-assigned"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/{countryCode}/{number}/owner", method = RequestMethod.DELETE)
    public void deassignNumber(
            @ApiParam("The country code of the number, e.g. +44") @PathVariable("countryCode") String countryCode,
            @ApiParam("The rest of the number, e.g. 8989505050") @PathVariable("number") String number) {
        numberService.deassignNumber(Instant.now(), PhoneNumber.of(countryCode, number));
    }

    @ApiOperation("Get the numbers currently owned by a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Numbers successfully retrieved"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/owners/{userId}/numbers", method = RequestMethod.GET)
    public List<PhoneNumberAndConfigurationView> getNumbersCurrentlyOwned(
            @ApiParam("The id of the user to retrieve numbers for") @PathVariable("userId") UUID userId) {
        return numberService.getNumbersCurrentlyOwnedBy(UserId.of(userId)).stream()
                .map(phoneNumberAndConfigurationMapper)
                .collect(Collectors.toList());
    }
}
