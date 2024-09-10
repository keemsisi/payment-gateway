package com.core.payment.processor.common.exceptions;

import com.core.payment.processor.common.dto.response.GenericApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.DefaultResponseErrorHandler;

import javax.validation.ConstraintViolationException;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends DefaultResponseErrorHandler {

    @ResponseBody
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<GenericApiResponse<?>> handleApplicationException(final ApplicationException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new GenericApiResponse<>(ex.getData(), ex.getMessage(), ex.getCode()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public GenericApiResponse<?> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        return new GenericApiResponse<>(null, ex.getMessage(), 400);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public GenericApiResponse<?> handleInternalServerExceptions(HttpMediaTypeNotSupportedException ex) {
        log.error(ex.getMessage(), ex);
        return new GenericApiResponse<>(null, ex.getMessage(), 400);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GenericApiResponse<?> handleInternalServerExceptions(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new GenericApiResponse<>(null, "Oops! An error occurred, please contact support!", 500);
    }


}
