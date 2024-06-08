package com.yape.fraudevaluation.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handle(Throwable ex) {
        log.error("Ocurrió un error en la aplicación {}", ex.toString());
        ex.printStackTrace();
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .description(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
