package com.demo.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest {

    @Test
    public void adding0Num() {
        // arrange
        Calculator calculator = new Calculator();
        int expectedSum = 0;

        // act
        int actualSum = calculator.add("");

        // assert
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void adding1Num() {
        // arrange
        Calculator calculator = new Calculator();
        int expectedSum = 1;

        // act
        int actualSum = calculator.add("1");

        // assert
        assertEquals(expectedSum, actualSum);
    }
}
