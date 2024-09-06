package com.core.payment.gateway.common.dto.request.bank;

import lombok.*;

@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankTransactionOwnerDTO {
    private String accountNumber;
    private String accountName;
    private String bankCode;
}
