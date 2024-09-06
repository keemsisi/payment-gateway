package com.core.payment.gateway.common.dto.request.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWalletDTO {
    private String name;
    private Long ownerId;
}
