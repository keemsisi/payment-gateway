package com.core.payment.gateway.service.core.transaction.card.schemes.verve;

import com.core.payment.gateway.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.gateway.service.core.transaction.card.schemes.CardSchemeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component(value = "verveCardScheme")
@AllArgsConstructor
public class VerveCardSchemeServiceImpl implements CardSchemeService {
    @Override
    public boolean validateExpiryDate(String expiryDate) {
        return false;
    }

    @Override
    public boolean validateCVV(String cvv) {
        return false;
    }

    @Override
    public TransactionResult authorizeTransaction(CardTransactionRequestDTO.CardDTO cardDTO, BigDecimal amount) {
        return null;
    }

    @Override
    public TransactionResult captureTransaction(String transactionId, double amount) {
        return null;
    }

    @Override
    public TransactionResult refundTransaction(String transactionId, double amount) {
        return null;
    }

    @Override
    public String getErrorDescription(String errorCode) {
        return "Readable error error code";
    }
}
