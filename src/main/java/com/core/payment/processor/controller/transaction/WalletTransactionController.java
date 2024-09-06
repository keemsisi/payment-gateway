package com.core.payment.processor.controller.transaction;

import com.core.payment.processor.common.dto.request.wallet.WalletTransactionRequestDTO;
import com.core.payment.processor.common.dto.response.GenericApiResponse;
import com.core.payment.processor.common.enums.ResponseCodeMapping;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.service.core.transaction.wallet.WalletTransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/transaction/wallet")
public class WalletTransactionController {
    private final WalletTransactionService transactionService;

    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Transaction>> initTransfer(@Valid @RequestBody WalletTransactionRequestDTO request) throws JsonProcessingException {
        final var transaction = transactionService.initTransfer(request);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.WALLET_TRANSACTION_INIT_OK.getMessage(),
                ResponseCodeMapping.WALLET_TRANSACTION_INIT_OK.getCode(), true), HttpStatus.OK);
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Transaction>> getByCardTransactionId(@PathVariable Long transactionId) throws JsonProcessingException {
        final var transaction = transactionService.getGetWalletTransactionById(transactionId);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.OK.getMessage(),
                ResponseCodeMapping.OK.getCode(), true), HttpStatus.OK);
    }

}
