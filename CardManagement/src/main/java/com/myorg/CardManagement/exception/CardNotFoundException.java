package com.myorg.CardManagement.exception;

public class CardNotFoundException extends RuntimeException{

    public CardNotFoundException(String message) {
        super(message);
    }
}
