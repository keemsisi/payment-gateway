package com.core.payment.gateway.common.dto.response.wallet;

import com.core.payment.gateway.common.dto.request.wallet.WalletTransactionOwnerDTO;
import com.core.payment.gateway.common.dto.response.TransactionResponseDTO;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransactionResponseDTO extends TransactionResponseDTO {
    private WalletTransactionOwnerDTO sender;
    private WalletTransactionOwnerDTO receiver;
}
