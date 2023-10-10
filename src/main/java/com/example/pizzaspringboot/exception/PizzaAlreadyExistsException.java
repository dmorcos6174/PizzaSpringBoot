package com.example.pizzaspringboot.exception;

public class PizzaAlreadyExistsException extends RuntimeException {

    public PizzaAlreadyExistsException(String message) {
        super(message);
    }
}
