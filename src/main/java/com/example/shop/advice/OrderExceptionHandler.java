package com.example.shop.advice;

import com.example.shop.exception.LowStockException;
import com.example.shop.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(LowStockException.class)
    public ErrorModel handleLowStockException(LowStockException exception) {
        return new ErrorModel(HttpStatus.CONFLICT.value(), exception.getMessage());

    }
}
