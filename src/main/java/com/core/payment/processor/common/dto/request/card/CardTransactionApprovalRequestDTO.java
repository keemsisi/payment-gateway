package com.core.payment.processor.common.dto.request.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardTransactionApprovalRequestDTO {
    @NotNull(message = "transactionId can't be null")
    private Long transactionId;
    @NotNull(message = "cardAuthCode can't be null")
    private Long cardAuthCode;
}
