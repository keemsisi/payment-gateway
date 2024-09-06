package com.core.payment.processor.common.dto.response;

import com.core.payment.processor.common.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private BigDecimal fee;
    private BigDecimal amount;
    private String reference;
    private LocalDateTime dateCreated;
    private LocalDateTime dateCompleted;
    private TransactionStatus status;
    private String description;
    private TransactionChannel channel;
    private Long transactionId;
}
