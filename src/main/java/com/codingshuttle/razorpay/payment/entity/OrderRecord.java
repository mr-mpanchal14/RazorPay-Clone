package com.codingshuttle.razorpay.payment.entity;

import com.codingshuttle.razorpay.common.entity.Money;
import com.codingshuttle.razorpay.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "order_record")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // no-FK - cross-service boundary, so we store merchantId as UUID instead of Merchant entity reference
    // This is because the OrderRecord entity is part of the payment service, while the Merchant entity is part of the merchant service. By storing the merchantId as a UUID, we can avoid creating a direct dependency between the two services, which can lead to tight coupling and make it harder to maintain and scale the system.
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;
    // The amount field is of type Money, which is an embeddable class that represents a monetary value with its currency. By using @Embedded, we indicate that the fields of the Money class should be mapped to columns in the order_record table. This allows us to store both the amount and its associated currency in the same table, making it easier to handle monetary values in a consistent manner.
    // This amount will be used inside the java code to create an order in Razorpay. The amount will be sent to Razorpay's API when creating the order, and it will be used to determine the total amount to be charged to the customer.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Integer attempts;

    @JdbcTypeCode((SqlTypes.JSON))
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> notes;
    // The notes field is a JSONB column that can store additional information related to the order. It is defined as a Map<String, Object> to allow for flexible key-value pairs. This can be useful for storing metadata or custom attributes that may vary between different orders.
    // The @JdbcTypeCode((SqlTypes.JSON)) annotation indicates that this field should be treated as a JSON type in the database, allowing for efficient storage and retrieval of JSON data.

    @Column(nullable = false)
    private LocalDateTime expiresAt;
}
