package com.myorg.customerManagement.exception;


import com.myorg.customerManagement.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handle(CustomerNotFoundException exception, WebRequest webRequest){
        System.out.println("In exc handler");
        ErrorResponseDto response = new ErrorResponseDto(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        System.out.println("In 2 class");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handle(Exception exception, WebRequest webRequest){
       // System.out.println("In exc handler");
        ErrorResponseDto response = new ErrorResponseDto(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        System.out.println("In 2 class");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
