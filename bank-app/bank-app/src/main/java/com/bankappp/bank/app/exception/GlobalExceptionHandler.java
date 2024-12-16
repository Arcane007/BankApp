package com.bankappp.bank.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handle specific exception -- AccountException
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorDetails> handleAccountException(AccountException exception, WebRequest web)
    {
        ErrorDetails details = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                web.getDescription(false),
                "ACCOUNT_NOT_FOUND");

        return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception exception,WebRequest webRequest)
    {
        ErrorDetails details = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR");

        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
