package com.example.shop.advice;

import com.example.shop.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorModel HandleInvalidArgument(MethodArgumentNotValidException exception){
        StringBuilder message= new StringBuilder();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
             message.append(fieldError.getField()).append(" : ").append(fieldError.getDefaultMessage()).append(" | ").toString();
        });
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(), message.toString());
    }
}
