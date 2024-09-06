package com.core.payment.gateway.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenericApiResponse<T> {
    private T data;
    private String message;
    private int code;
    private boolean success;
}
