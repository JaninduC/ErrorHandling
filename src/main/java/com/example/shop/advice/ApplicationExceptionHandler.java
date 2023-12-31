package com.example.shop.advice;

import com.example.shop.exception.InvalidRefreshToken;
import com.example.shop.exception.NotFoundException;
import com.example.shop.model.error.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
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
    public ErrorModel handleInvalidArgument(MethodArgumentNotValidException exception) {
        StringBuilder message = new StringBuilder();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            message.append(fieldError.getField()).append(" : ").append(fieldError.getDefaultMessage()).append(" | ");
        }
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(), message.toString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorModel handleCustomerNotFoundException(NotFoundException exception) {
        return new ErrorModel(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorModel handleSuperAllExceptions(Exception exception) {
        return new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorModel handleInvalidParameter(MissingServletRequestParameterException exception) {
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorModel handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorModel handleAccessDeniedException(AccessDeniedException exception) {
        return new ErrorModel(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorModel handleInvalidUsernameOrPasswordException(BadCredentialsException exception) {
        return new ErrorModel(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidRefreshToken.class)
    public ErrorModel handleInvalidUsernameOrPasswordException(InvalidRefreshToken exception) {
        return new ErrorModel(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }

}
