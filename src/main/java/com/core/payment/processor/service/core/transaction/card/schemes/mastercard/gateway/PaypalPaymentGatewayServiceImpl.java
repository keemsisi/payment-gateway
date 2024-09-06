package com.core.payment.processor.service.core.transaction.card.schemes.mastercard.gateway;

import com.core.payment.processor.service.core.transaction.card.gateway.PaymentGatewayService;
import com.core.payment.processor.service.core.transaction.card.schemes.CardSchemeService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaypalPaymentGatewayServiceImpl implements PaymentGatewayService {

    @Override
    public CardSchemeService.TransactionResult process() {
        return CardSchemeService.TransactionResult.builder()
                .success(true)
                .errorCode(null)
                .message("Payment successfully processed")
                .transactionId(RandomStringUtils.randomAlphabetic(10))
                .success(true)
                .build();
    }

    @Override
    public CardSchemeService.TransactionResult init() {
        return CardSchemeService.TransactionResult.builder()
                .success(true)
                .errorCode(null)
                .message("Transaction successfully initiated successfully")
                .transactionId(RandomStringUtils.randomAlphabetic(10))
                .success(true)
                .build();
    }

    @Override
    public CardSchemeService.TransactionResult reverse(String reference) {
        return CardSchemeService.TransactionResult.builder()
                .success(true)
                .errorCode(null)
                .message("Transaction was successfully reversed")
                .transactionId(reference)
                .success(true)
                .build();
    }
}
