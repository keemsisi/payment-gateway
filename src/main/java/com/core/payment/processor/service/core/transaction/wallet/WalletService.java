package com.core.payment.processor.service.core.transaction.wallet;

import com.core.payment.processor.common.dto.request.wallet.CreateWalletDTO;
import com.core.payment.processor.common.dto.request.wallet.WalletUpdateRequestDTO;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.entity.Wallet;

public interface WalletService {
    Wallet createWallet(final CreateWalletDTO request);

    Wallet getWalletById(final Long id);

    Wallet deleteWalletById(final Long id);

    Wallet updateWallet(final WalletUpdateRequestDTO updateRequestDTO);

    void save(Wallet wallet);

    void creditWallet(Transaction transaction, Wallet wallet);

    void debitWallet(Transaction transaction, Wallet wallet);
}
