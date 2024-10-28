package com.letterserviceapi.service.impl;

import com.letterserviceapi.common.constants.TopicConstants;
import com.letterserviceapi.common.dtos.CreateLetterRequest;
import com.letterserviceapi.common.dtos.CreateLetterResponse;
import com.letterserviceapi.common.dtos.LetterContentResponse;
import com.letterserviceapi.common.dtos.UpdateLetterRequest;
import com.letterserviceapi.common.entities.LetterModel;
import com.letterserviceapi.repositories.LetterRepository;
import com.letterserviceapi.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LetterServiceImpl implements LetterService {

    private final LetterRepository letterRepository;
    private final StreamBridge streamBridge;

    @Autowired
    public LetterServiceImpl(LetterRepository letterRepository, StreamBridge streamBridge) {

        this.letterRepository = letterRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public CreateLetterResponse createLetter(CreateLetterRequest createLetterRequest, Long userId) {

        return Optional.of(createLetterRequest)
                .map(this::mapToEntity)
                .map(letterRepository::save)
                .map(this::buildCreateLetterResponse)
                .map(this::sendLetterEvent)
                .orElseThrow(() -> new RuntimeException("Error creating a letter"));
    }

    private CreateLetterResponse sendLetterEvent(CreateLetterResponse createLetterResponse) {

        Optional.of(createLetterResponse)
                .map(givenLetter -> this.streamBridge.send(TopicConstants.LETTER_CREATED_TOPIC, createLetterResponse))
                .map(bool -> createLetterResponse);

        return createLetterResponse;
    }

    private CreateLetterResponse buildCreateLetterResponse(LetterModel savedLetter) {

        return CreateLetterResponse.builder()
                .trackingNumber(savedLetter.getTrackingNumber())
                .recipientEmail(savedLetter.getRecipientEmail())
                .build();
    }

    private LetterModel mapToEntity(CreateLetterRequest createLetterRequest) {

        return LetterModel.builder()
                .senderName(createLetterRequest.getSenderName())
                .senderAddress(createLetterRequest.getSenderAddress())
                .senderEmail(createLetterRequest.getSenderEmail())
                .senderPhoneNumber(createLetterRequest.getSenderPhoneNumber())
                .recipientName(createLetterRequest.getRecipientName())
                .recipientAddress(createLetterRequest.getRecipientAddress())
                .recipientEmail(createLetterRequest.getRecipientEmail())
                .recipientPhoneNumber(createLetterRequest.getRecipientPhoneNumber())
                .letterBody(createLetterRequest.getLetterBody())
                .letterWidth(createLetterRequest.getLetterWidth())
                .letterHeight(createLetterRequest.getLetterHeight())
                .letterWeight(createLetterRequest.getLetterWeight())
                .build();

    }

    @Override
    public LetterContentResponse getLetterContent(Long trackingNumber) {

        return Optional.of(trackingNumber)
                .map(this::getLetterByTrackingNumber)
                .map(this::mapToLetterContent)
                .orElseThrow(() -> new RuntimeException("Error letter not found by id"));

    }

    private LetterContentResponse mapToLetterContent(LetterModel letterModel) {

        return LetterContentResponse.builder()
                .recipientName(letterModel.getRecipientName())
                .recipientAddress(letterModel.getRecipientAddress())
                .recipientEmail(letterModel.getRecipientEmail())
                .letterSubject(letterModel.getLetterSubject())
                .letterWeight(letterModel.getLetterWeight())
                .build();

    }

    private LetterModel getLetterByTrackingNumber(Long trackingNumber) {

        return this.letterRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("Error finding letter by trackingNumber"));

    }

    @Override
    public void updateLetter(UpdateLetterRequest updateLetterRequest, Long trackingNumber) {

    }

    @Override
    public void deleteLetter(Long trackingNumber) {

    }
}
