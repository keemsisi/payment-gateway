package com.core.payment.processor.service.core.transaction.wallet;

import com.core.payment.processor.common.dto.request.wallet.WalletTransactionRequestDTO;
import com.core.payment.processor.common.dto.response.TransactionChannel;
import com.core.payment.processor.common.enums.ResponseCodeMapping;
import com.core.payment.processor.common.enums.TransactionStatus;
import com.core.payment.processor.common.exceptions.ApplicationException;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.entity.Wallet;
import com.core.payment.processor.service.core.transaction.TransactionServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {
    private final TransactionServiceImpl transactionService;
    private final WalletService walletService;
    private final ObjectMapper objectMapper;

    @Override
    public Transaction getById(Long transactionId) {
        return transactionService.getById(transactionId);
    }

    @Override
    public Transaction initAndProcess(final WalletTransactionRequestDTO request) throws JsonProcessingException {
        final var senderWallet = walletService.getWalletById(request.getSenderWalletId());
        final var receiverWallet = walletService.getWalletById(request.getReceiverWalletId());
        if (senderWallet.getBalanceAfter().compareTo(BigDecimal.ZERO) <= 0
                || senderWallet.getBalanceAfter().compareTo(request.getAmount()) <= 0) {
            final var responseCode = ResponseCodeMapping.WALLET_TRANSACTION_INSUFFICIENT_BALANCE;
            throw new ApplicationException(400, responseCode.getCode(), responseCode.getMessage());
        }
        final var transaction = create(request, senderWallet, receiverWallet);
        walletService.debitWallet(transaction, senderWallet);
        transaction.setDateCompleted(LocalDateTime.now());
        transaction.setDateUpdated(LocalDateTime.now());
        save(transaction);
        return transaction;
    }

    @Override
    public Transaction create(final WalletTransactionRequestDTO request, final Wallet senderWallet, final Wallet receiverWallet) throws JsonProcessingException {
        final var meta = objectMapper.writeValueAsString(request);
        final var transaction = Transaction.builder()
                .amount(request.getAmount())
                .senderAccountId(senderWallet.getAccountNumber())
                .senderName(senderWallet.getAccountName())
                .receiverAccountId(receiverWallet.getAccountNumber())
                .receiverName(receiverWallet.getAccountName())
                .fee(BigDecimal.TEN)
                .amount(request.getAmount())
                .channel(TransactionChannel.WALLET)
                .dateCompleted(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .metaData(meta).build();
        transaction.setDateCreated(LocalDateTime.now());
        return transactionService.save(transaction);
    }

    @Override
    public Transaction save(final Transaction transaction) {
        return transactionService.save(transaction);
    }
}
