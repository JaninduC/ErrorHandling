package com.example.shop.exception;

public class InvalidRefreshToken extends Exception {
    public InvalidRefreshToken(String message) {
        super(message);
    }
}
