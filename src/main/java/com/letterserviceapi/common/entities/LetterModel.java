package com.letterserviceapi.common.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "letter")
public class LetterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trackingNumber;

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

    @Min(value = 10, message = "Width must be at least 10 cm")
    @Schema(description = "Receives the width of a letter")
    private Float letterWidth;

    @Min(value = 10, message = "Height must be at least 10 cm")
    @Schema(description = "Receives the height of a letter")
    private Float letterHeight;

    @Min(value = 0, message = "Weight must be a positive value")
    @Schema(description = "Receives the weight of a letter which must be positive")
    private Float letterWeight;

    @Schema(description = "Creates a letter at the local current time")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
