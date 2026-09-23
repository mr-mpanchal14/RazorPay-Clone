package com.codingshuttle.razorpay.merchant.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // optional = false ensures that a customer must always be associated with a merchant
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
    private String email; // unique constraint is not applied here because multiple customers can have the same email address, especially if they are associated with different merchants.

    @Column(length = 20)
    private String contactNumber;

    private LocalDateTime deletedAt;
}
