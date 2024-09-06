package com.core.payment.gateway.common.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException{
    private final String code;
    @JsonIgnore
    private final int httpStatus;
    private Object data;

    public ApplicationException(int httpStatus, int code, String message) {
        super(message);
        this.code = String.valueOf(code);
        this.httpStatus = httpStatus;
    }
}
