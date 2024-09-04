package com.mllo.p2evik.exception;

/*
 * Custom exception for Role not found.
 */
public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String message) {
        super(message);
    }
}