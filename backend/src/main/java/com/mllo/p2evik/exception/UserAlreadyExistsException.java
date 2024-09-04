package com.mllo.p2evik.exception;

/*
 * Custom exception for User already exists.
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
