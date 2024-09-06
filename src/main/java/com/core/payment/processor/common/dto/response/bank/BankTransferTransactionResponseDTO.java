package com.core.payment.processor.common.dto.response.bank;

import com.core.payment.processor.common.dto.request.bank.BankTransactionOwnerDTO;
import com.core.payment.processor.common.dto.response.TransactionResponseDTO;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankTransferTransactionResponseDTO extends TransactionResponseDTO {
    private BankTransactionOwnerDTO sender;
    private BankTransactionOwnerDTO receiver;
}
