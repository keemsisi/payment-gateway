package com.core.payment.processor.service.core.transaction.wallet;

import com.core.payment.processor.common.dto.request.wallet.CreateWalletDTO;
import com.core.payment.processor.common.dto.request.wallet.WalletUpdateRequestDTO;
import com.core.payment.processor.common.enums.ResponseCodeMapping;
import com.core.payment.processor.common.exceptions.ApplicationException;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.entity.Wallet;
import com.core.payment.processor.repository.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {
    private static final String REF_TEMPLATE = "WAL_REF_%s";
    private final WalletRepository walletRepository;

    @Override
    public Wallet createWallet(final CreateWalletDTO request) {
        final var ref = String.format(REF_TEMPLATE, System.currentTimeMillis());
        final var wallet = new Wallet();
        wallet.setWalletType(Wallet.WalletType.PRIMARY);
        wallet.setReference(ref);
        wallet.setAccountName(request.getName());
        wallet.setOwnerId(request.getOwnerId());
        wallet.setAccountNumber(RandomStringUtils.randomNumeric(10));
        wallet.setDateCreated(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletById(final Long walletId) {
        final var wallet = walletRepository.findById(walletId).orElse(null);
        if (Objects.isNull(wallet)) {
            throw new ApplicationException(404, ResponseCodeMapping.NOT_FOUND.getCode(),
                    ResponseCodeMapping.NOT_FOUND.getMessage());
        }
        return wallet;
    }

    @Override
    public Wallet deleteWalletById(final Long id) {
        final var wallet = getWalletById(id);
        walletRepository.delete(wallet);
        return wallet;
    }

    @Override
    public Wallet updateWallet(final WalletUpdateRequestDTO updateRequestDTO) {
        final var wallet = getWalletById(updateRequestDTO.getWalletId());
        wallet.setAccountName(updateRequestDTO.getAccountName());
        return walletRepository.save(wallet);
    }

    @Override
    public void save(final Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public void creditWallet(final Transaction transaction, final Wallet wallet) {
        final var balanceBefore = wallet.getBalanceAfter();
        final var netAmount = balanceBefore.add(transaction.getAmount());
        final var balanceAfter = netAmount.subtract(transaction.getFee());
        wallet.setBalanceAfter(balanceAfter);
        wallet.setBalanceBefore(balanceBefore);
        wallet.setDateUpdated(LocalDateTime.now());
        wallet.setLastTransactionDate(LocalDateTime.now());
        walletRepository.save(wallet);
        log.info(">>> User wallet account[accountNumber: {}] credited with {} ",
                wallet.getAccountNumber(), transaction.getAmount());
    }

    @Override
    public void debitWallet(final Transaction transaction, final Wallet wallet) {
        final var balanceBefore = wallet.getBalanceAfter();
        final var netAmount = balanceBefore.subtract(transaction.getAmount());
        final var balanceAfter = netAmount.subtract(transaction.getFee());
        wallet.setBalanceAfter(balanceAfter);
        wallet.setBalanceBefore(balanceBefore);
        wallet.setDateUpdated(LocalDateTime.now());
        wallet.setLastTransactionDate(LocalDateTime.now());
        walletRepository.save(wallet);
        log.info(">>> User wallet account[accountNumber: {}] debited with {} ",
                wallet.getAccountNumber(), transaction.getAmount());
    }
}
