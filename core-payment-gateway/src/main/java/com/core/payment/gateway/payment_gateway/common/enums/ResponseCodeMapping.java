package com.core.payment.gateway.payment_gateway.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCodeMapping {
    OK(20000, "Request was successful"),
    NOT_FOUND(20001, "Resource not found!"),
    BAD_REQUEST(20002, "Oops! Bad Request!"),

    BANK_LOOKUP_OK(30000, "Bank lookup was successful"),
    BANK_TRANSFER_OK(30001, "Bank transfer was successful"),

    TRANSACTION_SUCCESSFUL(40000, "Transaction was successful"),
    TRANSACTION_FAILED(40001, "Transaction failed to process"),
    TRANSACTION_INVALID(40002, "Transaction experienced invalid params"),

    WALLET_TRANSACTION_CREDIT_OK(50000, "Transaction experienced invalid params"),
    WALLET_TRANSACTION_CREDIT_FAILED(50001, "Wallet transaction credit failed"),
    WALLET_TRANSACTION_DEBIT_FAILED(50002, "Wallet transaction debit failed"),

    CARD_TRANSACTION_CREDIT_OK(60000, "Card transaction was successful"),
    CARD_TRANSACTION_CREDIT_FAILED(60001, "Oops! Failed to credit wallet!"),
    CARD_TRANSACTION_DEBIT_FAILED(60002, "Oops! Failed to debit wallet!");

    private final int code;
    private final String message;

    ResponseCodeMapping(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
