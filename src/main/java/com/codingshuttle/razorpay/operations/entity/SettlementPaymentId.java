package com.codingshuttle.razorpay.operations.entity;

import jakarta.persistence.Embeddable;

import java.util.UUID;

// This class represents a composite primary key for the SettlementPayment entity. It consists of two UUID fields: settlementId and paymentId. The @Embeddable annotation indicates that this class can be embedded in another entity as a composite key.
// The SettlementPaymentId class is used to uniquely identify a relationship between a settlement and a payment in the database. It is typically used in conjunction with the @EmbeddedId annotation in the SettlementPayment entity.
@Embeddable
public class SettlementPaymentId {
    private UUID settlementId;
    private UUID paymentId;
}
