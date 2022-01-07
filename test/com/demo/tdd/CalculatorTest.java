package com.demo.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void create() {
        calculator = new Calculator();
    }

    @Test
    public void adding0Num() {
        // arrange
        long expectedSum = 0;

        // act
        long actualSum = calculator.add("");

        // assert
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void adding1Num() {
        // arrange
        long expectedSum = 1;

        // act
        long actualSum = calculator.add("1");

        // assert
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void adding2Num() {
        // arrange
        long expectedSum = 3;

        // act
        long actualSum = calculator.add("1,2");

        // assert
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void adding3Num() {
        // arrange
        long expectedSum = 6;

        // act
        long actualSum = calculator.add("1,2,3");

        // assert
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void adding5Num() {
        // arrange
        long expectedSum = 4999999999995L;

        // act
        long actualSum = calculator.add("999999999999,999999999999," +
                "999999999999,999999999999,999999999999");

        // assert
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void adding3NumWithNewLine() {
        // arrange
        long expectedSum = 6;

        // act
        long actualSum = calculator.add("1\n2,3");

        // assert
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void adding3NumWithSemiColonDelimiter() {
        // arrange
        long expectedSum = 6;

        // act
        long actualSum = calculator.add("//;\n1;2;3");

        // assert
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void adding3NumWithDollarDelimiter() {
        // arrange
        long expectedSum = 6;

        // act
        long actualSum = calculator.add("//$\n1$2$3");

        // assert
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void adding2NegativeNum() {
        // arrange
        String expectedExceptionMessage = "Negatives not allowed: -1, -2";

        try {
            // act
            calculator.add("-1,-2");
        } catch(RuntimeException e) {
            // assert
            assertEquals(expectedExceptionMessage, e.getMessage());
        }
    }
}
