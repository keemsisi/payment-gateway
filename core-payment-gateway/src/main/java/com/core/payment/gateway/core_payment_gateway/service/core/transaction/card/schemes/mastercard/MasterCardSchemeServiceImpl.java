package com.core.payment.gateway.core_payment_gateway.service.core.transaction.card.schemes.mastercard;

import com.core.payment.gateway.core_payment_gateway.service.core.transaction.card.schemes.CardSchemeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
    public TransactionResult authorizeTransaction(String cardNumber, String expiryDate, String cvv, double amount) {
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
