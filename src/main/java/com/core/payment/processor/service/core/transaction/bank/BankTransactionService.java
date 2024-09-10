package com.core.payment.processor.service.core.transaction.bank;

import com.core.payment.processor.common.dto.request.bank.BankAccountLookUpRequestDTO;
import com.core.payment.processor.common.dto.request.bank.BankTransferTransactionRequestDTO;
import com.core.payment.processor.common.dto.response.bank.BankAccountLookUpResponseDTO;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.service.core.transaction.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BankTransactionService extends TransactionService {
    Transaction initAndTransfer(BankTransferTransactionRequestDTO request) throws JsonProcessingException;

    Transaction getById(Long transactionId) throws JsonProcessingException;

    BankAccountLookUpResponseDTO lookUp(BankAccountLookUpRequestDTO request);

}
