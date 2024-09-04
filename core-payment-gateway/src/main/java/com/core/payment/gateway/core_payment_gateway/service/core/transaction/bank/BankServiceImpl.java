package com.core.payment.gateway.core_payment_gateway.service.core.transaction.bank;

import com.core.payment.gateway.core_payment_gateway.service.core.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    private final TransactionService transactionService;
}
