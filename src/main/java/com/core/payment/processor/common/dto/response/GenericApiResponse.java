package com.core.payment.processor.common.dto.response;

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

    public GenericApiResponse(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
        success = false;
    }
}
