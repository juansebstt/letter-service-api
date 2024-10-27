package com.letterserviceapi.common.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    private String senderEmail;

    @NotNull(message = "Sender phone is required")
    @NotBlank(message = "Sender phone cannot be blank")
    @Schema(description = "Full name of the letter sender")
    private String senderPhoneNumber;

    @NotNull(message = "Sender phone is required")
    @NotBlank(message = "Sender phone cannot be blank")
    @Schema(description = "Full name of the letter sender")
    private String recipientName;

    

}
