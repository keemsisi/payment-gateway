package com.core.payment.processor.service.core.transaction;

import com.core.payment.processor.common.enums.ResponseCodeMapping;
import com.core.payment.processor.common.exceptions.ApplicationException;
import com.core.payment.processor.entity.Transaction;
import com.core.payment.processor.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final ObjectMapper objectMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction getById(final Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ApplicationException(404, ResponseCodeMapping.NOT_FOUND.getCode(),
                        ResponseCodeMapping.NOT_FOUND.getMessage()));
    }

    @Override
    public Transaction save(final Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
