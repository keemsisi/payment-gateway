package com.core.payment.gateway.service.core.transaction.wallet;

import com.core.payment.gateway.common.dto.request.bank.BankTransferTransactionRequestDTO;
import com.core.payment.gateway.common.dto.request.wallet.CreateWalletDTO;
import com.core.payment.gateway.common.dto.request.wallet.WalletUpdateRequestDTO;
import com.core.payment.gateway.entity.Transaction;
import com.core.payment.gateway.entity.Wallet;

public interface WalletService {
    Wallet createWallet(final CreateWalletDTO request);

    Wallet getWalletById(final Long id);

    Wallet deleteWalletById(final Long id);

    Wallet updateWallet(final WalletUpdateRequestDTO updateRequestDTO);

    void save(Wallet wallet);

    void creditWallet(Transaction transaction, Wallet wallet);

    void debitWallet(Wallet wallet, BankTransferTransactionRequestDTO request);

    void debitWallet(Transaction transaction, Wallet wallet);
}
