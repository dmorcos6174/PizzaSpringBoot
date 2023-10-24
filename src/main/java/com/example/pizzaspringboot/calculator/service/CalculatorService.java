package com.example.pizzaspringboot.calculator.service;

import com.example.pizzaspringboot.calculator.exception.AddByZeroException;
import com.example.pizzaspringboot.calculator.exception.DivideByZeroException;
import com.example.pizzaspringboot.calculator.exception.MultiplyByZero;
import com.example.pizzaspringboot.calculator.exception.NegativeSubtractionException;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double add(double a, double b) {
        if (a == 0 || b == 0)
            throw new AddByZeroException("You can't add by zero!");
        return a + b;
    }

    public double subtract(double a, double b) {
        if (b > a)
            throw new NegativeSubtractionException("You can't subtract by a number bigger than first!");
        return a - b;
    }

    public double multiply(double a, double b) {
        if (a == 0 || b == 0)
            throw new MultiplyByZero("You can't multiply by zero!");
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0)
            throw new DivideByZeroException("You can't divide by zero!");
        return a / b;
    }
}
