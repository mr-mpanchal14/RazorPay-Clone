package com.codingshuttle.razorpay.merchant.entity;

import com.codingshuttle.razorpay.common.enums.Environment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

// The ApiKey entity represents an API key associated with a merchant. It contains information about the key, such as its ID, secret hash, environment, and status. The entity also tracks the last usage time, rotation time, and grace period expiration time for the API key.
// The ApiKey entity is linked to the Merchant entity through a many-to-one relationship, indicating that multiple API keys can belong to a single merchant. The entity uses UUIDs as primary keys for uniqueness and security.
// An API key is a unique identifier used to authenticate and authorize access to an API. It is typically used in applications to allow developers to access specific features or data provided by the API. In this context, the ApiKey entity is used to manage and store API keys for merchants, enabling them to securely interact with the system's APIs.
// Example: A merchant can have multiple API keys for different environments (e.g., live and test) or for different applications. Each API key has a unique key ID and a hashed secret, which is used for authentication when making API requests. The entity also tracks the last time the key was used, when it was last rotated, and when the grace period for the key expires, allowing for better management of API keys and their lifecycle.
@Entity
@Table(name = "api_key")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false, length = 50, unique = true)
    private String keyId;
    @Column(nullable = false, length = 200)
    private String keySecretHash;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Environment environment;

    @Column(nullable = false)
    private boolean enabled = true;

    private LocalDateTime lastUsedAt;
    private LocalDateTime rotatedAt;
    private LocalDateTime gracePeriodExpiresAt;
}
