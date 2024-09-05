package com.core.payment.gateway.payment_gateway.service.core.transaction.bank;

import com.core.payment.gateway.payment_gateway.common.dto.request.CreateBankRequestDTO;
import com.core.payment.gateway.payment_gateway.common.dto.response.GenericApiResponse;
import com.core.payment.gateway.payment_gateway.entity.Bank;

public interface BankService {
    GenericApiResponse<Bank> addBank(CreateBankRequestDTO request);

    GenericApiResponse<Bank> getAll(CreateBankRequestDTO request);

    GenericApiResponse<Bank> delete(CreateBankRequestDTO request);

    GenericApiResponse<Bank> update(CreateBankRequestDTO request);
}
