package com.core.payment.processor.controller.transaction;

import com.core.payment.processor.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.processor.common.dto.response.GenericApiResponse;
import com.core.payment.processor.common.enums.ResponseCodeMapping;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.service.core.transaction.card.CardTransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/transaction/card")
public class CardTransactionController {
    private final CardTransactionService transactionService;

    @PreAuthorize("hasAuthority('CARD_TRANSFER')")
    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Transaction>> initTransfer(@Valid @RequestBody CardTransactionRequestDTO request) throws JsonProcessingException {
        final var transaction = transactionService.initTransfer(request);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.CARD_TRANSACTION_INIT_OK.getMessage(),
                ResponseCodeMapping.CARD_TRANSACTION_INIT_OK.getCode(), true), HttpStatus.OK);
    }
}
