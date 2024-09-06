package com.core.payment.processor.service.core.transaction.card;

import com.core.payment.processor.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.service.core.transaction.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CardTransactionService extends TransactionService {
    Transaction initTransfer(CardTransactionRequestDTO request) throws JsonProcessingException;
}
