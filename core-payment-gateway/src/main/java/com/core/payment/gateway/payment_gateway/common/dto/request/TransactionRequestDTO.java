package com.core.payment.gateway.payment_gateway.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class TransactionRequestDTO {
    private BigDecimal amount;
    private String narration;
}
