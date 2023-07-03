package com.example.shop.advice;

import com.example.shop.exception.CustomerNotFoundException;
import com.example.shop.exception.InvalidParameter;
import com.example.shop.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorModel HandleInvalidArgument(MethodArgumentNotValidException exception) {
        StringBuilder message = new StringBuilder();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            message.append(fieldError.getField()).append(" : ").append(fieldError.getDefaultMessage()).append(" | ");
        });
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(), message.toString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorModel HandleCustomerNotFoundException(CustomerNotFoundException exception) {
        return new ErrorModel(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorModel HandleSuperAllExceptions(Exception exception) {
        return new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorModel HandleInvalidParameter(MissingServletRequestParameterException exception){
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(),exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorModel HandleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
