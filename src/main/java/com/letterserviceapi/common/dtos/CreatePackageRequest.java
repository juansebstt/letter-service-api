package com.letterserviceapi.common.dtos;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePackageRequest {

    private String recipientName;

    @Email
    private String receiverEmail;

    private String address;
    private String content;
    private Integer weight;

    @Email
    private String receiverEmail;

}
