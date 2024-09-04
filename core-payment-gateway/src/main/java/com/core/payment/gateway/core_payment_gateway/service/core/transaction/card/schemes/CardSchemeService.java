package com.core.payment.gateway.core_payment_gateway.service.core.transaction.card.schemes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface CardSchemeService {
    default boolean validateCardNumber(String cardNumber) {
        String cleanCardNumber = cardNumber.replaceAll("\\D", "");
        return !cleanCardNumber.isEmpty() && cleanCardNumber.matches("\\d+");
    }

    boolean validateExpiryDate(final String expiryDate);

    boolean validateCVV(final String cvv);

    TransactionResult authorizeTransaction(final String cardNumber, final String expiryDate, final String cvv, final double amount);

    TransactionResult captureTransaction(String transactionId, double amount);

    TransactionResult refundTransaction(String transactionId, double amount);

    String getErrorDescription(String errorCode);

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    class TransactionResult {
        private boolean success;
        private String transactionId;
        private String message;
        private String errorCode;
    }
}
