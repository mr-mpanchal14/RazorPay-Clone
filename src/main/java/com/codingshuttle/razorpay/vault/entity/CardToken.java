package com.codingshuttle.razorpay.vault.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


// CardToken entity represents a tokenized version of a vault card. It contains information about the token itself, the associated vault card, the customer and merchant IDs, and the revocation status of the token. The entity is mapped to the "card_token" table in the database.
@Entity
@Table(name = "card_token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50, unique = true)
    private String token;

    // One vault card can generate multiple card tokens, but one card token can only be associated with one vault card. Hence, the relationship is ManyToOne.
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // optional because a card token must be associated with a vault card, so it cannot be null.
    @JoinColumn(name = "vault_card_id", nullable = false)
    private VaultCard vaultCard;

    @Column(nullable = false)
    private UUID customer;

    @Column(nullable = false)
    private UUID merchant;

    private LocalDateTime revokedAt;
}
