package com.codingshuttle.razorpay.payment.entity;

import com.codingshuttle.razorpay.common.entity.Money;
import com.codingshuttle.razorpay.common.enums.RefundStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "merchant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    // The merchantId is stored as a UUID in the Refund entity. This allows for easy association of the refund with the corresponding merchant. The merchantId is a foreign key that references the Merchant entity, ensuring that each refund is linked to a valid merchant. This design choice helps maintain data integrity and allows for efficient querying of refunds based on the associated merchant.
    // @JoinColumn(nullable = false) means that the merchantId column in the Refund table cannot be null, ensuring that every refund is associated with a valid merchant. This is important for maintaining data integrity and ensuring that refunds are properly linked to the merchants they belong to.
    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefundStatus status = RefundStatus.PENDING;
    //Refund will have it's own lifecycle, so it will have its own status. The status can be PENDING, SUCCESS, FAILED, etc.
    // It will have it's own State Machine, which will be different from the Payment State Machine. The Refund State Machine will have states like PENDING, SUCCESS, FAILED, etc. The Refund State Machine will be triggered by the Payment State Machine. For example, when a Payment is captured, it will trigger the Refund State Machine to create a Refund.

    @Column(length = 100)
    private String bankReference;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 100)
    private String errorDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> notes;

    private LocalDateTime processedAt;
}
