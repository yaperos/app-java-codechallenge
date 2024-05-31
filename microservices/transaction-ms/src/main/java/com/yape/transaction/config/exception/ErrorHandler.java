package com.yape.transaction.config.exception;

import com.yape.transaction.application.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handle(Throwable ex) {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex);
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundException ex) {
        log.error(ErrorCode.TRANSACTION_NOT_FOUND.getCode(), ex);
        return buildResponseError(HttpStatus.NOT_FOUND, ErrorCode.TRANSACTION_NOT_FOUND);
    }

    @ExceptionHandler(KafkaException.class)
    public ResponseEntity<ErrorResponse> handle(KafkaException ex) {
        log.error(ex.getCode().getCode(), ex);
        return buildResponseError(HttpStatus.SERVICE_UNAVAILABLE, ex.getCode());
    }

    private ResponseEntity<ErrorResponse> buildCustomResponseError(HttpStatus httpStatus, ErrorCode errorCode, String customDescription) {
        final var response = ErrorResponse.builder()
            .errorInternalCode(errorCode.value())
            .errorDescription(customDescription)
            .errorCode(errorCode.getCode())
            .build();

        return new ResponseEntity<>(response, httpStatus);
    }

    private ResponseEntity<ErrorResponse> buildResponseError(HttpStatus httpStatus, ErrorCode errorCode) {
        return buildCustomResponseError(httpStatus, errorCode, errorCode.getReasonPhrase());
    }

}

