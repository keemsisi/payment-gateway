package com.core.payment.gateway.core_payment_gateway.controller.core;

import com.core.payment.gateway.core_payment_gateway.service.core.transaction.bank.BankService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/bank")
public class BankController {
    private final BankService walletService;
}
