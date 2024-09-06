package com.core.payment.gateway.repository;

import com.core.payment.gateway.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
