package com.codingshuttle.razorpay.merchant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

// MerchantWebhookConfig entity represents the configuration for webhooks for a merchant. It contains information about the target URL where the webhook events will be sent, the secret hash used for verifying the authenticity of the webhook requests, and the event types that the merchant wants to receive webhooks for. The entity is mapped to the "merchant_webhook_config" table in the database.
@Entity
@Table(name = "merchant_webhook_config")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantWebhookConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false, length = 500)
    private String targetUrl;
    // e.g. "https://zara.com/webhook"

    @Column(nullable = false, length = 255)
    private String webhookSecretHash;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(length = 255)
    private String eventTypes;
    // comma separated list of event types that the merchant wants to receive webhooks for. For example, "PAYMENT_SUCCESS,PAYMENT_FAILED,REFUND_INITIATED".
}
