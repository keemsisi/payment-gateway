package com.core.payment.processor.service.core.transaction.card.schemes.mastercard;

import com.core.payment.processor.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.processor.service.core.transaction.card.schemes.CardSchemeService;
import com.core.payment.processor.service.core.transaction.card.schemes.visa.gateway.StripePaymentGatewayServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component(value = "masterCardScheme")
@AllArgsConstructor
public class MasterCardSchemeServiceImpl implements CardSchemeService {
    private StripePaymentGatewayServiceImpl paymentGatewayService;

    @Override
    public TransactionResult authorizeTransaction(CardTransactionRequestDTO.CardDTO cardDTO, BigDecimal amount) {
        return paymentGatewayService.process();
    }

    @Override
    public TransactionResult captureTransaction(CardTransactionRequestDTO.CardDTO cardDTO, BigDecimal amount) {
        return paymentGatewayService.init();
    }

    @Override
    public TransactionResult refundTransaction(String transactionId, double amount) {
        return null;
    }

    @Override
    public String getErrorDescription(String errorCode) {
        return "Error description here!";
    }
}
