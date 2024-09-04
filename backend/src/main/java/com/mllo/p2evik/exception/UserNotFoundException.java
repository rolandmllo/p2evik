package com.mllo.p2evik.exception;

/*
 * Custom exception for User not found.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not found person with id: " + id);
    }
}
