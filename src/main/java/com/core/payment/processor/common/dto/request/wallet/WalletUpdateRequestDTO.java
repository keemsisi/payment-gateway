package com.core.payment.processor.common.dto.request.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletUpdateRequestDTO {
    private String accountName;
    private long walletId;
    private BigDecimal balanceAfter;
    private BigDecimal balanceBefore;
}
