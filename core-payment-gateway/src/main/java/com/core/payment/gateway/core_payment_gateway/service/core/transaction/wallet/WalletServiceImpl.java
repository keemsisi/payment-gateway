package com.core.payment.gateway.core_payment_gateway.service.core.transaction.wallet;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletTransactionService walletTransactionService;
}
