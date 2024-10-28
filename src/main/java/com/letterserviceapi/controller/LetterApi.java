package com.letterserviceapi.controller;

import com.letterserviceapi.common.constants.ApiPathConstants;
import com.letterserviceapi.common.dtos.CreateLetterRequest;
import com.letterserviceapi.common.dtos.CreateLetterResponse;
import com.letterserviceapi.common.dtos.LetterContentResponse;
import com.letterserviceapi.common.dtos.UpdateLetterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_Route + ApiPathConstants.LETTER_ROUTE)
public interface LetterApi {

    @PostMapping(value = "/create")
    ResponseEntity<CreateLetterResponse> createLetter(
            @RequestBody @Valid CreateLetterRequest createLetterRequest,
            @RequestParam(name = "X-User-Id") Long userId);

    @GetMapping
    ResponseEntity<LetterContentResponse> getLetterInformation(@RequestAttribute Long trackingNumber);

    @PutMapping(value = "/update_letter/{trackingNumber}")
    ResponseEntity<Void> updateLetter(
            @RequestBody @Valid UpdateLetterRequest updateLetterRequest,
            @PathVariable Long trackingNumber
    );

    @DeleteMapping(value = "/delete_package/{trackingNumber}")
    ResponseEntity<Void> deletePackage(@PathVariable Long trackingNumber);
}
