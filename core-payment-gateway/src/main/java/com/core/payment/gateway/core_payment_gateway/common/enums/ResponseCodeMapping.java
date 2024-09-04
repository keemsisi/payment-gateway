package com.core.payment.gateway.core_payment_gateway.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCodeMapping {
    OK(20000, "Request was successful"),
    TRANSACTION_SUCCESSFUL(20001, "Transaction was successful"),
    BANK_LOOKUP_OK(30001, "Bank lookup was successful"),
    BANK_TRANSFER_OK(30003, "Bank transfer was successful"),
    TRANSACTION_FAILED(40001, "Transaction failed to process"),
    TRANSACTION_INVALID(40002, "Transaction experienced invalid params"),
    WALLET_TRANSACTION_CREDIT_OK(50000, "Transaction experienced invalid params"),
    WALLET_TRANSACTION_CREDIT_FAILED(50001, "Wallet transaction credit failed"),
    WALLET_TRANSACTION_DEBIT_FAILED(50001, "Wallet transaction debit failed");
    private final int code;
    private final String description;

    ResponseCodeMapping(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
