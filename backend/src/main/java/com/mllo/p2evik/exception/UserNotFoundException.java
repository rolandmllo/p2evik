package com.mllo.p2evik.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not found person with id: " + id);
    }
}
