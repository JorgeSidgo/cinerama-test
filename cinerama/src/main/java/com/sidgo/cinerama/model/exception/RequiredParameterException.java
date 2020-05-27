package com.sidgo.cinerama.model.exception;

public class RequiredParameterException extends Exception{
    public RequiredParameterException(String errorMessage) {
        super(errorMessage);
    }
}
