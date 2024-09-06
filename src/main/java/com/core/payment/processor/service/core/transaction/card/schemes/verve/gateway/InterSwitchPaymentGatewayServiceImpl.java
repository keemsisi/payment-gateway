package com.core.payment.processor.service.core.transaction.card.schemes.verve.gateway;

import com.core.payment.processor.service.core.transaction.card.gateway.PaymentGatewayService;
import com.core.payment.processor.service.core.transaction.card.schemes.CardSchemeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InterSwitchPaymentGatewayServiceImpl implements PaymentGatewayService {

    @Override
    public CardSchemeService.TransactionResult process() {
        return null;
    }

    @Override
    public CardSchemeService.TransactionResult init() {
        return null;
    }

    @Override
    public CardSchemeService.TransactionResult reverse(String reference) {
        return null;
    }
}
