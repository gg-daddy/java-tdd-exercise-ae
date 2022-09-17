package com.odde.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void one_add_one(){
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 1);
        assertEquals(2, result);
    }
}
