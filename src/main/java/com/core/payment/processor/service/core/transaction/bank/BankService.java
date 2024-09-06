package com.core.payment.processor.service.core.transaction.bank;

import com.core.payment.processor.common.dto.request.bank.CreateBankRequestDTO;
import com.core.payment.processor.common.dto.response.GenericApiResponse;
import com.core.payment.processor.entity.Bank;

public interface BankService {
    GenericApiResponse<Bank> addBank(CreateBankRequestDTO request);

    GenericApiResponse<Bank> getAll(CreateBankRequestDTO request);

    GenericApiResponse<Bank> delete(CreateBankRequestDTO request);

    GenericApiResponse<Bank> update(CreateBankRequestDTO request);
}
