package com.core.payment.gateway.payment_gateway.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditWalletRequestDTO  extends TransactionRequestDTO{
    @NotBlank(message = "senderWalletId can't be blank")
    private String senderWalletId;
    @NotBlank(message = "receiverWalletId can't be blank")
    private String receiverWalletId;
}
