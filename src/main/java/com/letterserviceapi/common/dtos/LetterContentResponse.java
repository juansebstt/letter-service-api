package com.letterserviceapi.common.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LetterContentResponse {

    @Schema(description = "Receives the recipient name as a parameter")
    private String recipientName;

    @Schema(description = "Receives the recipient address as a parameter")
    private String recipientAddress;

    @Schema(description = "Receives the recipient email as a parameter")
    private String recipientEmail;

    @Schema(description = "Receives the letter content as a parameter")
    private String letterContent;

    @Schema(description = "Receives the letter weight name as a parameter")
    private Float letterWeight;

}
