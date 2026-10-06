package com.codingshuttle.razorpay.vault.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

// VaultCard entity represents a card stored in the vault. It contains information about the card such as the last four digits, BIN (Bank Identification Number), encrypted PAN (Primary Account Number), encrypted deck, brand, expiry month and year, cardholder name, and deletion date. The entity is mapped to the "vault_card" table in the database.
// We are creating a separate service of vaulting cards to make it PCI DSS compliant. The card details will be stored in the vault and the merchant will only have access to the last four digits and the BIN of the card. The encrypted PAN and deck will be stored in the vault and will be used for processing payments. The merchant will not have access to the full card number or any sensitive card information.
@Entity
@Table(name = "vault_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaultCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 4)
    private String lastFour;

    // Cannot store more than 6 digits of the BIN (Bank Identification Number) for security reasons. The BIN is the first 6 digits of the card number and is used to identify the issuing bank. Storing only the last 4 digits and the BIN allows for card identification without exposing sensitive card information.
    @Column(nullable = false, length = 6)
    private String bin;

    // The encrypted PAN (Primary Account Number) is stored as a byte array to ensure that the full card number is not exposed in the database. The PAN is encrypted using a secure encryption algorithm before being stored, and it can only be decrypted by authorized services that have access to the encryption keys. This approach helps maintain PCI DSS compliance by protecting sensitive cardholder data.
    @Column(nullable = false)
    private byte[] encryptedPan;

    // It will be used to encrypt the PAN and other sensitive card information. The encrypted dek is stored as a byte array to ensure that the full card number is not exposed in the database. The dek is encrypted using a secure encryption algorithm before being stored, and it can only be decrypted by authorized services that have access to the encryption keys. This approach helps maintain PCI DSS compliance by protecting sensitive cardholder data.
    // It will be generated via a randomizer and it should also be encrypted as we can't store the dek in plain text. So, a master key will be used to encrypt the dek which is generally used to encrypt all the sensitive data. The master key will be stored in a secure location and will be rotated periodically to ensure that the encrypted data remains secure.
    @Column(nullable = false)
    private byte[] encryptedDek;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String expiryMonth;

    @Column(nullable = false)
    private String expiryYear;

    @Column(nullable = false)
    private String cardHolderName;

    private LocalDate deletedAt;
}
