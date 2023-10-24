package com.example.pizzaspringboot.calculator.exception;

public class NegativeSubtractionException extends ArithmeticException {
    public NegativeSubtractionException(String s) {
        super(s);
    }
}
