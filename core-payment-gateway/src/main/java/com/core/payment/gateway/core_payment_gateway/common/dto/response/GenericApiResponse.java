package com.core.payment.gateway.core_payment_gateway.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenericApiResponse<T> {
    private T data;
    private String message;
    private String code;
    private boolean success;
}
