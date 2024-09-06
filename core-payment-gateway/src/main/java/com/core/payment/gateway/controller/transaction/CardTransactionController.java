package com.core.payment.gateway.controller.transaction;

import com.core.payment.gateway.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.gateway.common.dto.response.GenericApiResponse;
import com.core.payment.gateway.common.enums.ResponseCodeMapping;
import com.core.payment.gateway.entity.Transaction;
import com.core.payment.gateway.service.core.transaction.card.CardTransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/transaction/card")
public class CardTransactionController {
    private CardTransactionService transactionService;

    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Transaction>> initTransfer(@Valid @RequestBody CardTransactionRequestDTO request) throws JsonProcessingException {
        final var transaction = transactionService.initTransfer(request);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.CARD_TRANSACTION_INIT_OK.getMessage(),
                ResponseCodeMapping.CARD_TRANSACTION_INIT_OK.getCode(), true), HttpStatus.OK);
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Transaction>> getByCardTransactionId(@PathVariable Long transactionId) throws JsonProcessingException {
        final var transaction = transactionService.getCardTransactionById(transactionId);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.OK.getMessage(),
                ResponseCodeMapping.OK.getCode(), true), HttpStatus.OK);
    }

}
