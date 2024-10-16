package com.letterserviceapi.common.dtos;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePackageRequest {

    private Long trackingNumber;
    private String address;
    private String content;
    private Integer weight;

    @Email
    private String ReceiverEmail;
    
}
