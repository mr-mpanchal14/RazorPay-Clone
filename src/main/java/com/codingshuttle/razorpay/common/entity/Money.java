package com.codingshuttle.razorpay.common.entity;

import jakarta.persistence.Embeddable;


// This class represents a monetary value in a specific currency. It encapsulates the amount in the smallest currency unit (e.g., paise for INR) and the currency code (e.g., INR). The class provides methods to create Money instances, perform addition and subtraction operations, and ensure that operations are only performed between Money objects of the same currency.
// Example: If you want to represent an amount of 100 INR, you would create a Money object with amountUnits set to 10000 (since 1 INR = 100 paise) and currency set to "INR". You can then add or subtract other Money objects with the same currency, but attempting to add or subtract Money objects with different currencies will result in an exception.
@Embeddable
public class Money {
    private int amountUnits; // Amount in the smallest currency unit (e.g., paise for INR)
    private String currency; // Currency code (e.g., INR)

    private Money(int amountUnits, String currency) {
        this.amountUnits = amountUnits;
        this.currency = currency;
    }

    public static Money of(int amountUnits, String currency) {
        return new Money(amountUnits, currency);
    }

    public static Money inr(int amountInPaise) {
        return new Money(amountInPaise, "INR");
    }

    public Money add(Money money) {
        if(!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add Money objects with different currencies");
        }

        return new Money(this.amountUnits + money.amountUnits, this.currency);
    }

    public Money subtract(Money money) {
        if(!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot subtract Money objects with different currencies");
        }

        return new Money(this.amountUnits - money.amountUnits, this.currency);
    }
}
