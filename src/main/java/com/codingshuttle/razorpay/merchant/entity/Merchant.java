package com.codingshuttle.razorpay.merchant.entity;

import com.codingshuttle.razorpay.common.enums.BusinessType;
import com.codingshuttle.razorpay.common.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "merchant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Merchant {
    // UUID is used as the primary key for the Merchant entity. It is a universally unique identifier that can be used to uniquely identify a merchant across different systems and databases. Using UUIDs as primary keys can help prevent collisions and ensure that each merchant has a unique identifier, even if they are created in different systems or at different times.
    // It's a 16 digit hexadecimal number that is generated using a combination of the current time, a random number, and other factors. This makes it highly unlikely that two UUIDs will be the same, even if they are generated at the same time or in different systems.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 15)
    private String contactNumber;

    @Column(length = 50)
    private String businessName;
    @Column(length = 100)
    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    private BusinessType businessType;
    @Column(length = 200)
    private String websiteUrl;

    @Column(length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private MerchantStatus status = MerchantStatus.PENDING_KYC;

    @Column(length = 20)
    private String gstId;
    @Column(length = 20)
    private String panId;

    @Column(length = 200)
    private String settlementBankAccountNumber;
    @Column(length = 200)
    private String settlementBankAccountHolderName;
    @Column(length = 20)
    private String settlementBankIfscCode;

}
