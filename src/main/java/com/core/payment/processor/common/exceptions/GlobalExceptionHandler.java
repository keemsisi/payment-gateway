package com.core.payment.processor.common.exceptions;

import com.core.payment.processor.common.dto.response.GenericApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.DefaultResponseErrorHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends DefaultResponseErrorHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<GenericApiResponse<?>> handleAccessDeniedException(final AccessDeniedException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(403)
                .body(new GenericApiResponse<>(ex.getMessage(), ex.getMessage(), 403));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericApiResponse<Map<Object, Object>>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        final var errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(
                new GenericApiResponse<>(errors, "Request validation errors", 400),
                HttpStatus.BAD_REQUEST);
    }

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public GenericApiResponse<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage(), ex);
        return new GenericApiResponse<>(null, ex.getMessage(), 400);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public GenericApiResponse<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return new GenericApiResponse<>(null, "Request body is missing or bad request format!", 400);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericApiResponse<?>> handleInternalServerExceptions(final Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.internalServerError()
                .body(new GenericApiResponse<>(null, "Oops! An error occurred, please contact support!", 500));
    }
}
