package com.core.payment.gateway.controller.transaction;

import com.core.payment.gateway.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.gateway.common.dto.response.GenericApiResponse;
import com.core.payment.gateway.common.dto.response.card.CardTransactionResponseDTO;
import com.core.payment.gateway.common.enums.ResponseCodeMapping;
import com.core.payment.gateway.service.core.transaction.card.CardTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/transaction/card")
public class CardTransactionController {
    private CardTransactionService transactionService;

    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<CardTransactionResponseDTO>> initTransfer(@Valid @RequestBody CardTransactionRequestDTO request) {
        final var transaction = transactionService.initTransfer(request);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.BANK_TRANSFER_INIT_OK.getMessage(),
                ResponseCodeMapping.BANK_TRANSFER_INIT_OK.getCode(), true), HttpStatus.OK);
    }

}
