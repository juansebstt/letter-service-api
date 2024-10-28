package com.letterserviceapi.service.impl;

import com.letterserviceapi.common.dtos.CreateLetterRequest;
import com.letterserviceapi.common.dtos.CreateLetterResponse;
import com.letterserviceapi.common.dtos.LetterContentResponse;
import com.letterserviceapi.common.dtos.UpdateLetterRequest;
import com.letterserviceapi.service.LetterService;
import org.springframework.stereotype.Service;

@Service
public class LetterServiceImpl implements LetterService {
    @Override
    public CreateLetterResponse createLetter(CreateLetterRequest createLetterRequest, Long userId) {
        return null;
    }

    @Override
    public LetterContentResponse getLetterContent(Long trackingNumber) {
        return null;
    }

    @Override
    public void updateLetter(UpdateLetterRequest updateLetterRequest, Long trackingNumber) {

    }

    @Override
    public void deleteLetter(Long trackingNumber) {

    }
}
