package com.mllo.p2evik.exception;

/*
 * Custom exception for Resource not found.
 */
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}