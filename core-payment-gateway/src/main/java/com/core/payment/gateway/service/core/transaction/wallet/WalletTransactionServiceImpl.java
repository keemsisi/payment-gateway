package com.core.payment.gateway.service.core.transaction.wallet;

import com.core.payment.gateway.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.gateway.common.dto.response.card.CardTransactionResponseDTO;
import com.core.payment.gateway.entity.Transaction;
import com.core.payment.gateway.service.core.transaction.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {
    private final TransactionService transactionService;

    @Override
    public Transaction getById(Long transactionId) throws JsonProcessingException {
        return transactionService.getGetWalletTransactionById(transactionId);
    }

    @Override
    public CardTransactionResponseDTO initTransfer(CardTransactionRequestDTO request) {
        return null;
    }

    @Override
    public Transaction create(Transaction transaction) {
        return WalletTransactionService.super.create(transaction);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }
}
