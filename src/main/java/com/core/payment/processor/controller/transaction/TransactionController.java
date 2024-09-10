package com.core.payment.processor.controller.transaction;

import com.core.payment.processor.common.dto.response.GenericApiResponse;
import com.core.payment.processor.common.enums.ResponseCodeMapping;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.service.core.transaction.TransactionServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/transaction")
public class TransactionController {
    private final TransactionServiceImpl transactionService;

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Transaction>> getById(@PathVariable Long transactionId) throws JsonProcessingException {
        final var transaction = transactionService.getById(transactionId);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.OK.getMessage(),
                ResponseCodeMapping.OK.getCode(), true), HttpStatus.OK);
    }
}