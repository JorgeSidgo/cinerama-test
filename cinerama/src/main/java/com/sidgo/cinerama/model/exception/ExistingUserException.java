package com.sidgo.cinerama.model.exception;

public class ExistingUserException extends Exception {

    public ExistingUserException(String message) {
        super("There is already a user with this " + message);
    }

}
