package com.core.payment.processor.service.core.transaction.card.gateway;

import com.core.payment.processor.service.core.transaction.card.schemes.CardSchemeService;

public interface PaymentGatewayService {
    //define all the possible interfaces to talk the payment gateway to process the transaction
    CardSchemeService.TransactionResult process();

    CardSchemeService.TransactionResult init();

    CardSchemeService.TransactionResult reverse(final String reference);
}
