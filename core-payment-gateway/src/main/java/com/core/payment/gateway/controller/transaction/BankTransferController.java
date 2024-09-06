package com.core.payment.gateway.controller.transaction;

import com.core.payment.gateway.common.dto.request.bank.BankAccountLookUpRequestDTO;
import com.core.payment.gateway.common.dto.request.bank.BankTransferTransactionRequestDTO;
import com.core.payment.gateway.common.dto.response.GenericApiResponse;
import com.core.payment.gateway.common.dto.response.bank.BankAccountLookUpResponseDTO;
import com.core.payment.gateway.common.dto.response.bank.BankTransferTransactionResponseDTO;
import com.core.payment.gateway.common.enums.ResponseCodeMapping;
import com.core.payment.gateway.service.core.transaction.bank.BankTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/transaction/bank")
public class BankTransferController {
    private BankTransactionService transactionService;

    @RequestMapping(value = "/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<BankTransferTransactionResponseDTO>> initTransfer(@Valid @RequestBody BankTransferTransactionRequestDTO request) {
        final var transaction = transactionService.initTransfer(request);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.BANK_TRANSFER_INIT_OK.getMessage(),
                ResponseCodeMapping.BANK_TRANSFER_INIT_OK.getCode(), true), HttpStatus.OK);
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<BankAccountLookUpResponseDTO>> lookUp(@Valid @RequestBody BankAccountLookUpRequestDTO request) {
        final var transaction = transactionService.lookUp(request);
        return new ResponseEntity<>(new GenericApiResponse<>(transaction, ResponseCodeMapping.BANK_LOOKUP_OK.getMessage(),
                ResponseCodeMapping.BANK_LOOKUP_OK.getCode(), true), HttpStatus.OK);
    }
}
