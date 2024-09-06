package com.core.payment.processor.service.core.transaction.card.schemes;

import com.core.payment.processor.common.dto.request.card.CardTransactionRequestDTO;
import lombok.*;

import java.math.BigDecimal;

public interface CardSchemeService {
    default boolean validateCardNumber(String cardNumber) {
        String cleanCardNumber = cardNumber.replaceAll("\\D", "");
        return !cleanCardNumber.isEmpty() && cleanCardNumber.matches("\\d+");
    }

    boolean validateExpiryDate(final String expiryDate);

    boolean validateCVV(final String cvv);

    TransactionResult authorizeTransaction(final CardTransactionRequestDTO.CardDTO cardDTO, BigDecimal amount);

    TransactionResult captureTransaction(String transactionId, double amount);

    TransactionResult refundTransaction(String transactionId, double amount);

    String getErrorDescription(String errorCode);

    @Builder
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
