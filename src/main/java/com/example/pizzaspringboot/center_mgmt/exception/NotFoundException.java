package com.example.pizzaspringboot.center_mgmt.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
