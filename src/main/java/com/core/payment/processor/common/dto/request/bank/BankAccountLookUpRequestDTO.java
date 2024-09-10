package com.core.payment.processor.common.dto.request.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountLookUpRequestDTO {
    @NotBlank(message = "Account name must not be blank")
    private String accountName;
    @Max(value = 10, message = "Max account number length must be 10")
    private String accountNumber;
    @NotBlank(message = "Bank code can't be blank")
    private String bankCode;
}
