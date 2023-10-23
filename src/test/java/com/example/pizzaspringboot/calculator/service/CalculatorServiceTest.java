package com.example.pizzaspringboot.calculator.service;

import com.example.pizzaspringboot.service.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    public void testAdd() {
        double result = calculatorService.add(1, 2);
        Assertions.assertEquals(3, result);
    }

    @Test
    public void testSubtract() {
        double result = calculatorService.subtract(3, 1);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testMultiply() {
        double result = calculatorService.multiply(4, 5);
        Assertions.assertEquals(20, result);
    }

    @Test
    public void testDivide() {
        double result = calculatorService.divide(10, 2);
        Assertions.assertEquals(5, result);
    }
}
