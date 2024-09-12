package com.core.payment.processor.service.core.transaction.card;

import com.core.payment.processor.common.dto.request.card.CardTransactionApprovalRequestDTO;
import com.core.payment.processor.common.dto.request.card.CardTransactionRequestDTO;
import com.core.payment.processor.common.dto.response.TransactionChannel;
import com.core.payment.processor.common.enums.CardScheme;
import com.core.payment.processor.common.enums.ResponseCodeMapping;
import com.core.payment.processor.common.enums.TransactionStatus;
import com.core.payment.processor.common.exceptions.ApplicationException;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.entity.Wallet;
import com.core.payment.processor.service.core.transaction.TransactionServiceImpl;
import com.core.payment.processor.service.core.transaction.card.schemes.CardSchemeService;
import com.core.payment.processor.service.core.transaction.card.schemes.mastercard.MasterCardSchemeServiceImpl;
import com.core.payment.processor.service.core.transaction.card.schemes.verve.VerveCardSchemeServiceImpl;
import com.core.payment.processor.service.core.transaction.card.schemes.visa.VisaCardSchemeServiceImpl;
import com.core.payment.processor.service.core.transaction.wallet.WalletService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class CardTransactionServiceImpl implements CardTransactionService {
    private static final String MASKED_PAN_TEMP = "****-****-****-%s";
    private static final String CARD_GATEWAY_RESPONSE = "OK";
    private final TransactionServiceImpl transactionService;
    private final ObjectMapper objectMapper;
    private final WalletService walletService;
    private final ApplicationContext context;


    @Override
    public Transaction initTransfer(final CardTransactionRequestDTO request) throws JsonProcessingException {
        final var wallet = walletService.getWalletById(request.getWalletId());
        final var card = new CardTransactionRequestDTO.CardDTO();
        BeanUtils.copyProperties(request.getCard(), card);
        final var maskedPan = maskCardPan(card.getNumber());
        final var transaction = createTransaction(request, wallet);
        final var cardSchemeService = getCardSchemeProvider(card.getScheme());
        if (!cardSchemeService.isValid(card)) {
            final var responseCode = ResponseCodeMapping.CARD_VALIDATION_FAILED;
            throw new ApplicationException(400, responseCode.getCode(), responseCode.getMessage());
        }
        final var result = cardSchemeService.authorizeTransaction(card, request.getAmount());
        final var resultJson = objectMapper.writeValueAsString(result);
        transaction.setGatewayMeta(resultJson);
        if (result.isSuccess()) {
            transaction.setStatus(TransactionStatus.SUCCESS);
            transaction.setDateCompleted(LocalDateTime.now());
            transaction.setDateUpdated(LocalDateTime.now());
            transactionService.save(transaction);
            log.info(">>> Card Init transaction[{}] completed successfully!", maskedPan);
            return transaction;
        }
        throw new ApplicationException(400, ResponseCodeMapping.TRANSACTION_FAILED.getCode(),
                ResponseCodeMapping.TRANSACTION_FAILED.getMessage());
    }

    @Override
    public Transaction approve(final CardTransactionApprovalRequestDTO request) throws JsonProcessingException {
        final var transaction = transactionService.getById(request.getTransactionId());
        if (!transaction.getChannel().equals(TransactionChannel.CARD)) {
            throw new ApplicationException(400, ResponseCodeMapping.TRANSACTION_FAILED.getCode(), ResponseCodeMapping.TRANSACTION_FAILED.getMessage());
        }
        final var cardRequest = objectMapper.readValue(transaction.getMetaData(), CardTransactionRequestDTO.class);
        final var cardScheme = cardRequest.getCard().getScheme();
        final var walletId = cardRequest.getWalletId();
        final var wallet = walletService.getWalletById(walletId);
        final var cardSchemeService = getCardSchemeProvider(cardScheme);
        final var cardTransactionResponse = cardSchemeService.authorizeTransaction(cardRequest.getCard(), transaction.getAmount());
        if (cardTransactionResponse.isSuccess()) {
            walletService.creditWallet(transaction, wallet);
            transaction.setDateUpdated(LocalDateTime.now());
            transaction.setDateCompleted(LocalDateTime.now());
            transaction.setStatus(TransactionStatus.SUCCESS);
            return transactionService.save(transaction);
        }
        throw new ApplicationException(402, ResponseCodeMapping.TRANSACTION_FAILED.getCode(), ResponseCodeMapping.TRANSACTION_FAILED.getMessage());
    }

    private Transaction buildCardTransactionResponse() throws JsonProcessingException {
        return transactionService.getCardTransactionById(1L);
    }

    private CardSchemeService getCardSchemeProvider(final CardScheme scheme) {
        return switch (scheme) {
            case MASTERCARD -> (MasterCardSchemeServiceImpl) context.getBean("masterCardScheme");
            case VISA -> (VisaCardSchemeServiceImpl) context.getBean("visaCardScheme");
            case VERVE -> (VerveCardSchemeServiceImpl) context.getBean("verveCardScheme");
        };
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionService.save(transaction);
    }

    private Transaction createTransaction(final CardTransactionRequestDTO request, final Wallet wallet) throws JsonProcessingException {
        final var maskedPan = maskCardPan(request.getCard().getNumber());
        request.getCard().setNumber(maskedPan);
        request.getCard().setCvv("***");
        final var meta = objectMapper.writeValueAsString(request);
        final var transaction = Transaction.builder()
                .amount(request.getAmount())
                .senderAccountId(request.getCard().getNumber())
                .senderName(request.getCard().getName())
                .receiverAccountId(wallet.getAccountNumber())
                .receiverName(wallet.getAccountName())
                .fee(BigDecimal.ONE)
                .amount(request.getAmount())
                .channel(TransactionChannel.CARD)
                .dateCompleted(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .metaData(meta).build();
        transaction.setDateCreated(LocalDateTime.now());
        return transactionService.save(transaction);
    }

    private String maskCardPan(final String pan) {
        return String.format(MASKED_PAN_TEMP, pan.substring(pan.length() - 4));
    }
}

