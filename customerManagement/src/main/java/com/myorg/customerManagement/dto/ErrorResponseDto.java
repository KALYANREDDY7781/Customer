package com.myorg.customerManagement.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDto {
    private String apiPath;
    private String errorDescription;
    private HttpStatus httpStatus;
    private LocalDateTime errorTime;

    public ErrorResponseDto(String apiPath, String errorDescription, HttpStatus httpStatus, LocalDateTime errorTime) {
        this.apiPath = apiPath;
        this.errorDescription = errorDescription;
        this.httpStatus = httpStatus;
        this.errorTime = errorTime;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(LocalDateTime errorTime) {
        this.errorTime = errorTime;
    }
}
