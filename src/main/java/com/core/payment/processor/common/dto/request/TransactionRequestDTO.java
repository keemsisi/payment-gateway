package com.core.payment.processor.common.dto.request;

import com.core.payment.processor.common.dto.response.TransactionChannel;
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
    private TransactionChannel channel;
    private String narration;
    private BigDecimal amount;
}
