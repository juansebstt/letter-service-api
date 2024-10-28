package com.letterserviceapi.common.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLetterRequest {

    @Schema(description = "Recipient's name (optional). Leave blank if no update is needed.")
    private String recipientName;

    @Schema(description = "Recipient's address (optional). Leave blank if no update is needed.")
    private String recipientAddress;

    @Email(message = "Recipient email should be valid")
    @Schema(description = "Recipient's email (optional). Leave blank if no update is needed.")
    private String recipientEmail;

    @Schema(description = "Recipient's phone number (optional). Leave blank if no update is needed.")
    private String recipientPhoneNumber;

}
