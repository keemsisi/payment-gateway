package com.core.payment.processor.service.core.transaction.card.schemes.mastercard;

import com.core.payment.processor.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.processor.service.core.transaction.card.schemes.CardSchemeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component(value = "masterCardScheme")
@AllArgsConstructor
public class MasterCardSchemeServiceImpl implements CardSchemeService {

    @Override
    public boolean validateExpiryDate(String expiryDate) {
        return false;
    }

    @Override
    public boolean validateCVV(String cvv) {
        return false;
    }

    @Override
    public TransactionResult authorizeTransaction(final CardTransactionRequestDTO.CardDTO cardDTO, BigDecimal amount) {
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
        return "";
    }
}
