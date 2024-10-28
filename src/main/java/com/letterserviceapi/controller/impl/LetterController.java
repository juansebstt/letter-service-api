package com.letterserviceapi.controller.impl;

import com.letterserviceapi.common.dtos.CreateLetterRequest;
import com.letterserviceapi.common.dtos.CreateLetterResponse;
import com.letterserviceapi.common.dtos.LetterContentResponse;
import com.letterserviceapi.common.dtos.UpdateLetterRequest;
import com.letterserviceapi.controller.LetterApi;
import com.letterserviceapi.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LetterController implements LetterApi {

    private final LetterService letterService;

    @Autowired
    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @Override
    public ResponseEntity<CreateLetterResponse> createLetter(CreateLetterRequest createLetterRequest, Long userId) {
        return ResponseEntity.ok(letterService.createLetter(createLetterRequest, userId));
    }

    @Override
    public ResponseEntity<LetterContentResponse> getLetterInformation(Long trackingNumber) {
        return ResponseEntity.ok(letterService.getLetterContent(trackingNumber));
    }

    @Override
    public ResponseEntity<Void> updateLetter(UpdateLetterRequest updateLetterRequest, Long trackingNumber) {
        letterService.updateLetter(updateLetterRequest, trackingNumber);
        return ResponseEntity
                .noContent()
                .build();
    }

    @Override
    public ResponseEntity<Void> deleteLetter(Long trackingNumber) {
        letterService.deleteLetter(trackingNumber);
        return ResponseEntity
                .noContent()
                .build();
    }
}
