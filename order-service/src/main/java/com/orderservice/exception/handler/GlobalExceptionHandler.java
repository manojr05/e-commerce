package com.orderservice.exception.handler;

import com.orderservice.exception.QuantityNotAvailableException;
import com.orderservice.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(QuantityNotAvailableException.class)
    public ResponseEntity<Object> handleQuantityNotFoundException(QuantityNotAvailableException exception){

        ErrorResponse build = ErrorResponse.builder()
                .errorCode(404)
                .message(exception.getMessage())
                .date(new Date())
                .build();
        return new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
    }

}
