package com.core.payment.processor.common.dto.response.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountLookUpResponseDTO {
    private String accountName;
    private String accountNumber;
    private String bankName;
    private String bankCode;
}
