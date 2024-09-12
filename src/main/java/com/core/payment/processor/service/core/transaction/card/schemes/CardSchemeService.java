package com.core.payment.processor.service.core.transaction.card.schemes;

import com.core.payment.processor.common.dto.request.card.CardTransactionRequestDTO;
import lombok.*;

import java.math.BigDecimal;

public interface CardSchemeService {
    default boolean validateCardNumber(String cardNumber) {
        return CardUtils.validateCardNumber(cardNumber);
    }

    default boolean validateExpiryDate(final String expiryDate) {
        return CardUtils.validateExpiryDate(expiryDate);
    }

    default boolean validateCVV(final String cvv) {
        return CardUtils.validateCVV(cvv);
    }

    TransactionResult authorizeTransaction(final CardTransactionRequestDTO.CardDTO cardDTO, BigDecimal amount);

    TransactionResult captureTransaction(String transactionId, double amount);

    TransactionResult refundTransaction(String transactionId, double amount);

    String getErrorDescription(String errorCode);

    default boolean isValid(CardTransactionRequestDTO.CardDTO card) {
        final var isCVVValid = validateCVV(card.getCvv());
        final var isCardValid = validateCardNumber(card.getNumber());
        final var isExpiryDateValid = validateExpiryDate(card.getExpiry());
        return isCardValid && isCVVValid && isExpiryDateValid;
    }

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
