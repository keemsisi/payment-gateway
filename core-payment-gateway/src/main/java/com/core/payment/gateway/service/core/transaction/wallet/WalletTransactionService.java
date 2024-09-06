package com.core.payment.gateway.service.core.transaction.wallet;

import com.core.payment.gateway.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.gateway.common.dto.response.card.CardTransactionResponseDTO;
import com.core.payment.gateway.service.core.transaction.TransactionService;

public interface WalletTransactionService extends TransactionService {
    CardTransactionResponseDTO initTransfer(CardTransactionRequestDTO request);
}
