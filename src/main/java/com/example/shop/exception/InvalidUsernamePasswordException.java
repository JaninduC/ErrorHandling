package com.example.shop.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidUsernamePasswordException extends BadCredentialsException {
    public InvalidUsernamePasswordException(String message) {
        super(message);
    }
}
