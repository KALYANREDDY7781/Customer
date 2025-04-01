package com.myorg.CardManagement.exception;

import com.myorg.CardManagement.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handle(CardNotFoundException exception, WebRequest webRequest){
        ErrorResponseDto response = new ErrorResponseDto(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handle(Exception exception, WebRequest webRequest){
        ErrorResponseDto response = new ErrorResponseDto(webRequest.getDescription(false),
                exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
