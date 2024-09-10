package com.core.payment.processor.common.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException{
    private final int code;
    @JsonIgnore
    private final int httpStatus;
    private Object data;

    public ApplicationException(int httpStatus, int code, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
