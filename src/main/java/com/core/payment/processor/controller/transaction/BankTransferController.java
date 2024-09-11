package com.core.payment.processor.controller.transaction;

import com.core.payment.processor.common.dto.request.bank.BankAccountLookUpRequestDTO;
import com.core.payment.processor.common.dto.request.bank.BankTransferTransactionRequestDTO;
import com.core.payment.processor.common.dto.response.GenericApiResponse;
import com.core.payment.processor.common.dto.response.bank.BankAccountLookUpResponseDTO;
import com.core.payment.processor.common.enums.ResponseCodeMapping;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.service.core.transaction.bank.BankTransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/transaction/bank")
public class BankTransferController {
    private BankTransactionService transactionService;

    @PreAuthorize("hasAuthority('BANK_TRANSFER')")
    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Transaction>> initAndTransfer(@Valid @RequestBody BankTransferTransactionRequestDTO request) throws JsonProcessingException {
        final var transaction = transactionService.initAndTransfer(request);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.BANK_TRANSFER_INIT_OK.getMessage(),
                ResponseCodeMapping.BANK_TRANSFER_INIT_OK.getCode(), true), HttpStatus.OK);
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<BankAccountLookUpResponseDTO>> lookUp(@RequestBody BankAccountLookUpRequestDTO request) {
        final var transaction = transactionService.lookUp(request);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.BANK_LOOKUP_OK.getMessage(),
                ResponseCodeMapping.BANK_LOOKUP_OK.getCode(), true), HttpStatus.OK);
    }
}
