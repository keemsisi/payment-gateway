package com.core.payment.processor.service.core.transaction.card.schemes.visa;

import com.core.payment.processor.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.processor.service.core.transaction.card.schemes.CardSchemeService;
import com.core.payment.processor.service.core.transaction.card.schemes.visa.gateway.StripePaymentGatewayServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component(value = "visaCardScheme")
@AllArgsConstructor
public class VisaCardSchemeServiceImpl implements CardSchemeService {
    private StripePaymentGatewayServiceImpl paymentGatewayService;

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
        return paymentGatewayService.process();
    }

    @Override
    public TransactionResult captureTransaction(String transactionId, double amount) {
        return paymentGatewayService.init();
    }

    @Override
    public TransactionResult refundTransaction(String transactionId, double amount) {
        return null;
    }

    @Override
    public String getErrorDescription(String errorCode) {
        return "Readable error code";
    }
}
