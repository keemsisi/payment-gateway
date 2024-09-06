package com.core.payment.gateway.common.dto.request.bank;

import com.core.payment.gateway.common.dto.request.TransactionRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankTransferTransactionRequestDTO extends TransactionRequestDTO {
    @NotBlank(message = "Account number can't be blank")
    private String accountNumber;
    @NotBlank(message = "Account name can't be blank")
    private String accountName;
    @NotBlank(message = "Bank code can't be blank")
    private String bankCode;
    private Long walletId;
}
