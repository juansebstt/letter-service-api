package com.letterserviceapi.common.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "packages")
public class PackageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trackingNumber;

    // Package dimensions
    @NotNull(message = "Sender name is required")
    @NotBlank(message = "Sender name cannot be blank")
    private String senderName;

    @Email(message = "Sender email should be valid")
    private String senderEmail;

    @NotNull(message = "Sender address is required")
    @NotBlank(message = "Sender address cannot be blank")
    private String senderAddress;

    @NotNull(message = "Sender phone is required")
    @NotBlank(message = "Sender phone cannot be blank")
    @Pattern(regexp = "^[+]?[0-9]{1,15}$", message = "Invalid phone number format")
    private String senderPhone;

    // Recipient information
    @NotNull(message = "Recipient name is required")
    @NotBlank(message = "Recipient name cannot be blank")
    private String recipientName;

    @Email(message = "Recipient email should be valid")
    private String recipientEmail;

    @NotNull(message = "Recipient address is required")
    @NotBlank(message = "Recipient address cannot be blank")
    private String recipientAddress;

    @NotNull(message = "Recipient phone is required")
    @NotBlank(message = "Recipient phone cannot be blank")
    @Pattern(regexp = "^[+]?[0-9]{1,15}$", message = "Invalid phone number format")
    private String recipientPhoneNumber;

    //Package Dimensions
    @Min(value = 15, message = "Width must be at least 15 cm")
    private Float width;

    @Min(value = 15, message = "Height must be at least 15 cm")
    private Float height;

    @Min(value = 15, message = "Length must be at least 15 cm")
    private Float length;

    @Min(value = 0, message = "Weight must be a positive value")
    private Float weight;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
