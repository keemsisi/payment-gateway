package com.core.payment.gateway.service.core.transaction.wallet;

import com.core.payment.gateway.common.dto.request.wallet.WalletTransactionRequestDTO;
import com.core.payment.gateway.entity.Transaction;
import com.core.payment.gateway.entity.Wallet;
import com.core.payment.gateway.service.core.transaction.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface WalletTransactionService extends TransactionService {
    Transaction getById(Long transactionId) throws JsonProcessingException;

    Transaction initTransfer(WalletTransactionRequestDTO request) throws JsonProcessingException;


    Transaction create(WalletTransactionRequestDTO request, Wallet sender, Wallet receiver) throws JsonProcessingException;
}
