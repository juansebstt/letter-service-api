package com.letterserviceapi.common.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
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
    private String senderName;
    @Email
    private String senderEmail;
    private String senderAddress;
    private String senderPhone;

    // Recipient information
    private String recipientName;
    @Email
    private String recipientEmail;
    private String recipientAddress;
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
