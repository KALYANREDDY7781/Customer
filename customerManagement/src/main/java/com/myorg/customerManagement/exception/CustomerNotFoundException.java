package com.myorg.customerManagement.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
