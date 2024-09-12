package com.core.payment.processor.common.dto.request.card;

import com.core.payment.processor.common.dto.request.TransactionRequestDTO;
import com.core.payment.processor.common.enums.CardScheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardTransactionRequestDTO extends TransactionRequestDTO {
    @NotNull(message = "card detail can't be null")
    private CardDTO card;
    @NotNull(message = "walletId can't be null")
    private Long walletId;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardDTO {
        @NotBlank(message = "card number can't be blank")
        private String number;
        @NotBlank(message = "card cvv number can't be blank")
        private String cvv;
        @NotBlank(message = "card expiry can't be blank")
        private String expiry;
        @NotNull(message = "card scheme can't be blank")
        private CardScheme scheme;
        @NotNull(message = "name can't be blank")
        private String name;
    }
}
