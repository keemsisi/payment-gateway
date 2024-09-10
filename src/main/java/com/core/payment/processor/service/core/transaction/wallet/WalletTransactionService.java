package com.core.payment.processor.service.core.transaction.wallet;

import com.core.payment.processor.common.dto.request.wallet.WalletTransactionRequestDTO;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.entity.Wallet;
import com.core.payment.processor.service.core.transaction.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface WalletTransactionService extends TransactionService {
    Transaction getById(Long transactionId) throws JsonProcessingException;

    Transaction initAndProcess(WalletTransactionRequestDTO request) throws JsonProcessingException;


    Transaction create(WalletTransactionRequestDTO request, Wallet sender, Wallet receiver) throws JsonProcessingException;
}
