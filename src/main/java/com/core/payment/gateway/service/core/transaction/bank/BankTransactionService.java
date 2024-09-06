package com.core.payment.gateway.service.core.transaction.bank;

import com.core.payment.gateway.common.dto.request.bank.BankAccountLookUpRequestDTO;
import com.core.payment.gateway.common.dto.request.bank.BankTransferTransactionRequestDTO;
import com.core.payment.gateway.common.dto.response.bank.BankAccountLookUpResponseDTO;
import com.core.payment.gateway.entity.Transaction;
import com.core.payment.gateway.service.core.transaction.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BankTransactionService extends TransactionService {
    Transaction initTransfer(BankTransferTransactionRequestDTO request) throws JsonProcessingException;

    Transaction getById(Long transactionId) throws JsonProcessingException;

    BankAccountLookUpResponseDTO lookUp(BankAccountLookUpRequestDTO request);

}
