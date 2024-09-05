package com.core.payment.gateway.payment_gateway.service.core.transaction.wallet;

import com.core.payment.gateway.payment_gateway.common.dto.request.CreateWalletDTO;
import com.core.payment.gateway.payment_gateway.common.dto.request.WalletUpdateRequestDTO;
import com.core.payment.gateway.payment_gateway.entity.Wallet;

public interface WalletService {
    Wallet createWallet(final CreateWalletDTO request);

    Wallet getWalletById(final Long id);

    Wallet deleteWalletById(final Long id);

    Wallet updateWallet(final WalletUpdateRequestDTO updateRequestDTO);
}
