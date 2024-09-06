package com.core.payment.gateway.service.core.transaction.bank;

import com.core.payment.gateway.common.dto.request.bank.BankAccountLookUpRequestDTO;
import com.core.payment.gateway.common.dto.request.bank.BankTransferTransactionRequestDTO;
import com.core.payment.gateway.common.dto.response.TransactionChannel;
import com.core.payment.gateway.common.dto.response.bank.BankAccountLookUpResponseDTO;
import com.core.payment.gateway.common.enums.ResponseCodeMapping;
import com.core.payment.gateway.common.enums.TransactionStatus;
import com.core.payment.gateway.common.exceptions.ApplicationException;
import com.core.payment.gateway.entity.Transaction;
import com.core.payment.gateway.service.core.transaction.TransactionService;
import com.core.payment.gateway.service.core.transaction.wallet.WalletService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
@Transactional
public class BankTransactionServiceImpl implements BankTransactionService {
    private static final String PLATFORM_BANK_CODE = "066";
    private static final Logger log = LoggerFactory.getLogger(BankTransactionServiceImpl.class);
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final ObjectMapper objectMapper;

    @Override
    public Transaction initTransfer(final BankTransferTransactionRequestDTO request) throws JsonProcessingException {
        final var wallet = walletService.getWalletById(request.getWalletId());//get wallet to debit;
        final var hasBalance = wallet.getBalanceAfter().compareTo(BigDecimal.ZERO) > 0;
        final var transaction = createTransaction(request);
        if (hasBalance) {
            walletService.debitWallet(wallet, request);
            log.info(">>> Wallet debit was successful for bank transfer{}", request);
        } else {
            final var responseCode = ResponseCodeMapping.WALLET_TRANSACTION_INSUFFICIENT_BALANCE;
            throw new ApplicationException(400, responseCode.getCode(), responseCode.getMessage());
        }
        walletService.save(wallet);
        return transaction;
    }

    @Override
    public Transaction getById(final Long transactionId) throws JsonProcessingException {
        return transactionService.getBankTransferTransactionById(transactionId);
    }

    @Override
    public BankAccountLookUpResponseDTO lookUp(final BankAccountLookUpRequestDTO request) {
        return BankAccountLookUpResponseDTO
                .builder().bankCode("214").bankName("Adeshina Lasisi")
                .bankName("Fist City Monument Bank (FCMB)").build();
    }

    private Transaction createTransaction(final BankTransferTransactionRequestDTO request) throws JsonProcessingException {
        final var wallet = walletService.getWalletById(request.getWalletId());
        final var meta = objectMapper.writeValueAsString(request);
        final var transaction = Transaction.builder()
                .amount(request.getAmount())
                .senderAccountId(wallet.getAccountNumber())
                .senderName(wallet.getAccountName())
                .receiverAccountId(request.getAccountNumber())
                .receiverName(request.getAccountName())
                .fee(BigDecimal.TEN)
                .channel(TransactionChannel.BANK)
                .dateCompleted(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .metaData(meta)
                .build();
        transaction.setDateCreated(LocalDateTime.now());
        return create(transaction);
    }

    @Override
    public Transaction create(Transaction transaction) {
        return transactionService.create(transaction);
    }

    @Override
    public Transaction save(final Transaction transaction) {
        return transactionService.save(transaction);
    }
}
