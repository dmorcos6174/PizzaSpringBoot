package com.example.pizzaspringboot.calculator.exception;

public class DivideByZeroException extends ArithmeticException {
    public DivideByZeroException(String s) {
        super(s);
    }
}
