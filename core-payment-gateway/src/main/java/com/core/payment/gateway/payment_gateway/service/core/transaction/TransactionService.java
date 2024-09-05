package com.core.payment.gateway.payment_gateway.service.core.transaction;

import com.core.payment.gateway.payment_gateway.common.exceptions.NotImplementedException;

public interface TransactionService {
    String NOT_IMPLEMENTED = "Oops! Not Implemented!";

    default void create() {
        throw new NotImplementedException(400, NOT_IMPLEMENTED);
    }

    void process();

    void reverse();

    void credit();

    void debit();
}
