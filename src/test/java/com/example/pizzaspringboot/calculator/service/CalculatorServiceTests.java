package com.example.pizzaspringboot.calculator.service;

import com.example.pizzaspringboot.calculator.exception.AddByZeroException;
import com.example.pizzaspringboot.calculator.exception.DivideByZeroException;
import com.example.pizzaspringboot.calculator.exception.MultiplyByZero;
import com.example.pizzaspringboot.calculator.exception.NegativeSubtractionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTests {

    private final CalculatorService calculatorService = new CalculatorService();

    // Happy scenario testing
    @Test
    public void add_ValidParameters_ReturnsCorrectResult() {
        double result = calculatorService.add(1, 2);
        Assertions.assertEquals(3, result);
    }

    @Test
    public void subtract_ValidParameters_ReturnsCorrectResult() {
        double result = calculatorService.subtract(3, 1);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void multiply_ValidParameters_ReturnsCorrectResult() {
        double result = calculatorService.multiply(4, 5);
        Assertions.assertEquals(20, result);
    }

    @Test
    public void divide_ValidParameters_ReturnsCorrectResult() {
        double result = calculatorService.divide(10, 2);
        Assertions.assertEquals(5, result);
    }

    // Exception testing
    @Test
    public void add_InvalidParameters_ThrowsException() {
        Assertions.assertThrows(AddByZeroException.class, () -> {
            calculatorService.add(0, 2);
        });
    }

    @Test
    public void subtract_InvalidParameters_ThrowsException() {
        Assertions.assertThrows(NegativeSubtractionException.class, () -> {
            calculatorService.subtract(1, 2);
        });
    }

    @Test
    public void multiply_InvalidParameters_ThrowsException() {
        Assertions.assertThrows(MultiplyByZero.class, () -> {
            calculatorService.multiply(0, 2);
        });
    }

    @Test
    public void divide_InvalidParameters_ThrowsException() {
        Assertions.assertThrows(DivideByZeroException.class, () -> {
            calculatorService.divide(2, 0);
        });
    }
}
