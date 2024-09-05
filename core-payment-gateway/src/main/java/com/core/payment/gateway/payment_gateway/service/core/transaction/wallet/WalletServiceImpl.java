package com.core.payment.gateway.payment_gateway.service.core.transaction.wallet;

import com.core.payment.gateway.payment_gateway.common.dto.request.CreateWalletDTO;
import com.core.payment.gateway.payment_gateway.common.dto.request.WalletUpdateRequestDTO;
import com.core.payment.gateway.payment_gateway.common.enums.ResponseCodeMapping;
import com.core.payment.gateway.payment_gateway.common.exceptions.ApplicationException;
import com.core.payment.gateway.payment_gateway.entity.Wallet;
import com.core.payment.gateway.payment_gateway.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

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
        wallet.setDateCreated(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletById(final Long walletId) {
        final var wallet = walletRepository.findById(walletId).orElse(null);
        if (Objects.isNull(wallet)) {
            throw new ApplicationException(404, ResponseCodeMapping.NOT_FOUND.getCode(),
                    ResponseCodeMapping.OK.getMessage());
        }
        return wallet;
    }

    @Override
    public Wallet deleteWalletById(Long id) {
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
}
