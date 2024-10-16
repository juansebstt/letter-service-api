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

    @Min(15)
    private float width;

    @Min(15)
    private float height;

    @Min(15)
    private float length;

    private float weight;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
