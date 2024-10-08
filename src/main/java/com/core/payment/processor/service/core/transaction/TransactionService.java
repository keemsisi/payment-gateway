package com.core.payment.processor.service.core.transaction;

import com.core.payment.processor.common.exceptions.NotImplementedException;
import com.core.payment.processor.entity.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;

public interface TransactionService {
    String NOT_IMPLEMENTED = "Oops! Not Implemented!";

    default Transaction create(final Transaction transaction) {
        throw new NotImplementedException(HttpStatus.NOT_IMPLEMENTED.value(), NOT_IMPLEMENTED);
    }

    default Transaction getById(final Long id) throws JsonProcessingException {
        throw new NotImplementedException(HttpStatus.NOT_IMPLEMENTED.value(), NOT_IMPLEMENTED);
    }

    Transaction save(final Transaction transaction);
}
