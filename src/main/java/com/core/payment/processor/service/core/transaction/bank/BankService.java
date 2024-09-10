package com.core.payment.processor.service.core.transaction.bank;

import com.core.payment.processor.common.dto.request.bank.CreateBankRequestDTO;
import com.core.payment.processor.entity.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankService {
    Bank addBank(CreateBankRequestDTO request);

    Page<Bank> getAll(Pageable request);

    Bank delete(Long bankId);
}
