package com.example.shop.exception;

public class LowStockException extends Exception {
    public LowStockException(String message) {
        super(message);
    }
}
