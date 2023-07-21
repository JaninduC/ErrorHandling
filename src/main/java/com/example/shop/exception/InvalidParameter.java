package com.example.shop.exception;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.MissingServletRequestParameterException;

public class InvalidParameter extends MissingServletRequestParameterException {

    private final String parameterName;
    private final String parameterType;

    public InvalidParameter(String parameterName, String parameterType) {
        super(parameterName, parameterType);
        this.parameterName = parameterName;
        this.parameterType = parameterType;
    }

    @NonNull
    @Override
    public String getMessage() {
        return "invalid Parameter " + this.parameterName + " parameter type : " + this.parameterType;
    }
}
