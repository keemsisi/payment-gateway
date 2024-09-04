package com.core.payment.gateway.core_payment_gateway.repository;

import com.core.payment.gateway.core_payment_gateway.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
