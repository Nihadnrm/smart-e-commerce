package com.example.users.exception;

public class DuplicateUser extends RuntimeException {
    public DuplicateUser(String message) {
        super(message);
    }

}
