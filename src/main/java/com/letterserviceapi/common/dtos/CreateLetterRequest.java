package com.letterserviceapi.common.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLetterRequest {

    @NotNull(message = "Sender name is required")
    @NotBlank(message = "Sender name cannot be blank")
    @Schema(description = "Full name of the letter sender")
    private String senderName;

    @NotNull(message = "Sender email is required")
    @NotBlank(message = "Sender email cannot be blank")
    @Schema(description = "Full name of the letter sender")
    @Email
    private String senderEmail;

    @NotNull(message = "Sender phone is required")
    @NotBlank(message = "Sender phone cannot be blank")
    @Schema(description = "Full name of the letter sender")
    private String senderPhoneNumber;

    @NotNull(message = "Sender address is required")
    @NotBlank(message = "Sender address cannot be blank")
    @Schema(description = "Address of the letter sender")
    private String senderAddress;

    @NotNull(message = "Recipient phone is required")
    @NotBlank(message = "Recipient phone cannot be blank")
    @Schema(description = "Full name of the letter recipient")
    private String recipientName;

    @NotNull(message = "Recipient email is required")
    @NotBlank(message = "Recipient email cannot be blank")
    @Schema(description = "Full name of the letter recipient")
    @Email
    private String recipientEmail;

    @NotNull(message = "Recipient phone is required")
    @NotBlank(message = "Recipient phone cannot be blank")
    @Schema(description = "Full name of the letter recipient")
    private String recipientPhoneNumber;

    @NotNull(message = "Recipient address is required")
    @NotBlank(message = "Recipient address cannot be blank")
    @Schema(description = "Address of the letter recipient")
    private String recipientAddress;

    @Min(value = 10, message = "Width must be at least 10 cm")
    @Schema(description = "Receives the width of a letter")
    private Float letterWidth;

    @Min(value = 10, message = "Height must be at least 10 cm")
    @Schema(description = "Receives the height of a letter")
    private Float letterHeight;

    @Min(value = 0, message = "Weight must be a positive value")
    @Schema(description = "Receives the weight of a letter which must be positive")
    private Float letterWeight;

    @NotNull(message = "Letter subject is required")
    @NotBlank(message = "Letter subject cannot be blank")
    @Schema(description = "Receives the letter subject")
    private String letterSubject;

    @NotNull(message = "Letter body is required")
    @NotBlank(message = "Letter body cannot be blank")
    @Schema(description = "Receives the letter body")
    private String letterBody;

    @Schema(description = "Creates a letter at the local current time")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {

        createdAt = LocalDateTime.now();
    }


}
