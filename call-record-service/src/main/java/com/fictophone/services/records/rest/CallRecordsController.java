package com.fictophone.services.records.rest;

import com.fictophone.services.records.services.api.RecordsService;
import com.fictophone.services.records.views.CallRecordView;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fictophone.services.records.mappers.CallRecordMappers.callRecord2View;
import static com.fictophone.services.records.mappers.CallRecordMappers.view2Record;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/call-records")
public class CallRecordsController {

    private final RecordsService recordsService;

    @Autowired
    public CallRecordsController(RecordsService recordsService) {
        this.recordsService = recordsService;
    }

    @ApiOperation("Create record")
    @ApiResponses(
            {@ApiResponse(code = 200, message = "Record created"),
             @ApiResponse(code = 500, message = "Internal error")}
    )
    @RequestMapping(name = "/records", method = RequestMethod.POST)
    public void addRecord(
            @ApiParam("The call record to store") @RequestBody CallRecordView record) {
        recordsService.addRecord(view2Record.apply(record));
    }

    @ApiOperation("Retrieve records")
    @ApiResponses(
            {@ApiResponse(code = 200, message = "Records retrieved"),
             @ApiResponse(code = 500, message = "Internal error")}
    )
    @RequestMapping(name = "/records", method = RequestMethod.GET)
    public List<CallRecordView> retrieveRecords() {
        return recordsService.retrieveRecords().stream()
                .map(callRecord2View)
                .collect(toList());
    }

}
