package com.core.payment.gateway.common.dto.response.card;

import com.core.payment.gateway.common.dto.request.card.CardTransactionOwnerDTO;
import com.core.payment.gateway.common.dto.response.TransactionResponseDTO;
import com.core.payment.gateway.common.dto.request.wallet.WalletTransactionOwnerDTO;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardTransactionResponseDTO extends TransactionResponseDTO {
    private CardTransactionOwnerDTO sender;
    private WalletTransactionOwnerDTO receiver;
}
