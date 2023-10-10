package com.example.pizzaspringboot.exception;

public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException(String message) {
        super(message);
    }
}
