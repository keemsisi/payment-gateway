package com.core.payment.gateway.common.dto.request.wallet;

import com.core.payment.gateway.common.dto.request.TransactionRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionRequestDTO extends TransactionRequestDTO {
    @NotBlank(message = "senderWalletId can't be blank")
    private String senderWalletId;
    @NotBlank(message = "receiverWalletId can't be blank")
    private String receiverWalletId;
}
