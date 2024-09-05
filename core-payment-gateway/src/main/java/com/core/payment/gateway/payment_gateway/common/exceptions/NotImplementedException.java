package com.core.payment.gateway.payment_gateway.common.exceptions;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NotImplementedException extends RuntimeException {
    private final int status;
    private final String message;

    public NotImplementedException(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
