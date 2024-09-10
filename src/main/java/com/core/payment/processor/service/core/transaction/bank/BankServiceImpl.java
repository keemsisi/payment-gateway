package com.core.payment.processor.service.core.transaction.bank;

import com.core.payment.processor.common.dto.request.bank.CreateBankRequestDTO;
import com.core.payment.processor.common.enums.ResponseCodeMapping;
import com.core.payment.processor.common.exceptions.ApplicationException;
import com.core.payment.processor.entity.Bank;
import com.core.payment.processor.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Override
    public Bank addBank(final CreateBankRequestDTO request) {
        final var newBank = new Bank();
        newBank.setCode(request.getCode());
        newBank.setName(request.getName());
        newBank.setIconUrl(request.getIconUrl());
        newBank.setDateCreated(LocalDateTime.now());
        return bankRepository.save(newBank);
    }

    @Override
    public Page<Bank> getAll(final Pageable pageable) {
        return bankRepository.findAll(pageable);
    }

    @Override
    public Bank delete(Long bankId) {
        final var bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new ApplicationException(404, ResponseCodeMapping.NOT_FOUND.getCode(),
                        ResponseCodeMapping.NOT_FOUND.getMessage()));
        bankRepository.delete(bank);
        return bank;
    }
}
