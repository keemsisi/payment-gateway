package com.core.payment.gateway.payment_gateway.repository;

import com.core.payment.gateway.payment_gateway.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
