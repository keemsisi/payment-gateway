package com.core.payment.processor.common.dto.request.wallet;

import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransactionOwnerDTO {
    private Long walletId;
    private String walletName;
    private String accountNumber;
}
