package com.letterserviceapi.service;

import com.letterserviceapi.common.dtos.CreateLetterRequest;
import com.letterserviceapi.common.dtos.CreateLetterResponse;
import com.letterserviceapi.common.dtos.LetterContentResponse;
import com.letterserviceapi.common.dtos.UpdateLetterRequest;

public interface LetterService {

    CreateLetterResponse createLetter(CreateLetterRequest createLetterRequest, Long userId);

    LetterContentResponse getLetterContent(Long trackingNumber);

    void updateLetter(UpdateLetterRequest updateLetterRequest, Long trackingNumber);

    void deleteLetter(Long trackingNumber);
}
