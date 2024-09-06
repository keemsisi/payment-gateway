package com.core.payment.gateway.service.core.transaction.card;

import com.core.payment.gateway.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.gateway.entity.Transaction;
import com.core.payment.gateway.service.core.transaction.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CardTransactionService extends TransactionService {
    Transaction initTransfer(CardTransactionRequestDTO request) throws JsonProcessingException;
}
