package com.core.payment.processor.common.dto.request.wallet;

import com.core.payment.processor.common.dto.request.TransactionRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionRequestDTO extends TransactionRequestDTO {
    @NotNull(message = "senderWalletId can't be blank")
    private Long senderWalletId;
    @NotNull(message = "receiverWalletId can't be blank")
    private Long receiverWalletId;
}
