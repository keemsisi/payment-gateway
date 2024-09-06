package com.core.payment.processor.service.core.transaction;

import com.core.payment.processor.common.dto.request.bank.BankTransactionOwnerDTO;
import com.core.payment.processor.common.dto.request.card.CardTransactionOwnerDTO;
import com.core.payment.processor.common.dto.request.wallet.WalletTransactionOwnerDTO;
import com.core.payment.processor.common.dto.response.TransactionChannel;
import com.core.payment.processor.common.dto.response.bank.BankTransferTransactionResponseDTO;
import com.core.payment.processor.common.dto.response.card.CardTransactionResponseDTO;
import com.core.payment.processor.common.dto.response.wallet.WalletTransactionResponseDTO;
import com.core.payment.processor.common.enums.CardScheme;
import com.core.payment.processor.common.enums.TransactionStatus;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final ObjectMapper objectMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction getBankTransferTransactionById(final Long transactionId) throws JsonProcessingException {
        final var sender = BankTransactionOwnerDTO
                .builder().bankCode(RandomStringUtils.randomNumeric(3))
                .accountNumber(RandomStringUtils.randomNumeric(10))
                .accountName("Adeshina Lasisi").build();
        final var receiver = BankTransactionOwnerDTO
                .builder().bankCode("003")
                .accountNumber(RandomStringUtils.randomNumeric(10))
                .accountName("Jonson Abel").build();
        final var response = BankTransferTransactionResponseDTO
                .builder().receiver(receiver).sender(sender).build();
        response.setAmount(new BigDecimal(1000));
        response.setChannel(TransactionChannel.BANK);
        response.setFee(new BigDecimal(10));
        response.setDateCompleted(LocalDateTime.now());
        response.setDateCreated(LocalDateTime.now().minusDays(1));
        final var metaData = objectMapper.writeValueAsString(response);
        return Transaction.builder()
                .dateCompleted(LocalDateTime.now())
                .fee(response.getFee())
                .amount(response.getAmount())
                .receiverName("Adeshina Lasisi")
                .channel(TransactionChannel.BANK)
                .status(TransactionStatus.SUCCESS)
                .senderAccountId("Olojuwon Adeshina")
                .metaData(metaData)
                .build();
    }

    @Override
    public Transaction getCardTransactionById(final Long transactionId) throws JsonProcessingException {
        final var sender = CardTransactionOwnerDTO
                .builder().pan("4444 4444 **** **** 6666")
                .scheme(CardScheme.VISA)
                .accountName("Adeshina Lasisi")
                .build();
        final var receiver = WalletTransactionOwnerDTO
                .builder().walletId(1L).walletName("Adeshina Omolola").build();
        final var response = CardTransactionResponseDTO
                .builder().receiver(receiver).sender(sender).build();
        response.setAmount(new BigDecimal(1000));
        response.setChannel(TransactionChannel.CARD);
        response.setFee(new BigDecimal(10));
        response.setDateCompleted(LocalDateTime.now());
        response.setDateCreated(LocalDateTime.now().minusDays(1));
        final var metaData = objectMapper.writeValueAsString(response);
        return Transaction.builder()
                .dateCompleted(LocalDateTime.now())
                .fee(BigDecimal.TEN)
                .receiverName(receiver.getAccountNumber())
                .channel(TransactionChannel.BANK)
                .status(TransactionStatus.SUCCESS)
                .senderAccountId(sender.getAccountName())
                .metaData(metaData)
                .build();
    }

    @Override
    public Transaction getGetWalletTransactionById(final Long transactionId) throws JsonProcessingException {
        final var sender = WalletTransactionOwnerDTO
                .builder().walletId(1L)
                .walletName("Halimah John")
                .accountNumber(RandomStringUtils.randomNumeric(10)).build();
        final var receiver = WalletTransactionOwnerDTO
                .builder().walletId(2L).walletName("Tosin Halimah").build();
        final var response = WalletTransactionResponseDTO
                .builder().receiver(receiver).sender(sender).build();
        response.setAmount(new BigDecimal(2000));
        response.setChannel(TransactionChannel.WALLET);
        response.setFee(new BigDecimal(10));
        response.setDateCompleted(LocalDateTime.now());
        response.setDateCreated(LocalDateTime.now().minusDays(1));
        final var metaData = objectMapper.writeValueAsString(response);
        return Transaction.builder()
                .dateCompleted(LocalDateTime.now())
                .fee(BigDecimal.TEN)
                .receiverName(receiver.getWalletName())
                .channel(TransactionChannel.BANK)
                .status(TransactionStatus.SUCCESS)
                .senderAccountId(sender.getWalletName())
                .metaData(metaData)
                .build();
    }

    @Override
    public Transaction save(final Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
