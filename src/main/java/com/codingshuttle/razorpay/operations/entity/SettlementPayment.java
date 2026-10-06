package com.codingshuttle.razorpay.operations.entity;

import jakarta.persistence.*;
import lombok.*;

// This class represents the relationship between a settlement and a payment in the database. It uses a composite primary key defined by the SettlementPaymentId class, which consists of two UUID fields: settlementId and paymentId. The @EmbeddedId annotation indicates that the composite key is embedded in this entity. The @MapsId annotation is used to map the settlement field to the settlementId part of the composite key, establishing a many-to-one relationship with the Settlement entity.
@Entity
@Table(name = "settlement_payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementPayment {
    // Composite primary key for the SettlementPayment entity, consisting of settlementId and paymentId
    @EmbeddedId
    private SettlementPaymentId id;

    // Many-to-one relationship with the Settlement entity, mapped to the settlementId part of the composite key
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "settlement_id", nullable = false)
    private Settlement settlement;
}
