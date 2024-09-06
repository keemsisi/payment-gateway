package com.core.payment.processor.common.dto.request.card;

import com.core.payment.processor.common.enums.CardScheme;
import lombok.*;

@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardTransactionOwnerDTO {
    private String pan;
    private CardScheme scheme;
    private String accountName;
}
