package com.core.payment.gateway.service.core.transaction;

import com.core.payment.gateway.common.exceptions.NotImplementedException;
import com.core.payment.gateway.entity.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;

public interface TransactionService {
    String NOT_IMPLEMENTED = "Oops! Not Implemented!";

    default Transaction create(final Transaction transaction) {
        throw new NotImplementedException(HttpStatus.NOT_IMPLEMENTED.value(), NOT_IMPLEMENTED);
    }

    default Transaction getBankTransferTransactionById(final Long transactionId) throws JsonProcessingException {
        throw new NotImplementedException(HttpStatus.NOT_IMPLEMENTED.value(), NOT_IMPLEMENTED);
    }

    default Transaction getCardTransactionById(final Long transactionId) throws JsonProcessingException {
        throw new NotImplementedException(HttpStatus.NOT_IMPLEMENTED.value(), NOT_IMPLEMENTED);
    }

    default Transaction getGetWalletTransactionById(final Long transactionId) throws JsonProcessingException {
        throw new NotImplementedException(HttpStatus.NOT_IMPLEMENTED.value(), NOT_IMPLEMENTED);
    }

    Transaction save(final Transaction transaction);
}
