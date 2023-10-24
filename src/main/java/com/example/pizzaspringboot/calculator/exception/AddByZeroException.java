package com.example.pizzaspringboot.calculator.exception;

public class AddByZeroException extends ArithmeticException {
    public AddByZeroException(String s) {
        super(s);
    }
}
