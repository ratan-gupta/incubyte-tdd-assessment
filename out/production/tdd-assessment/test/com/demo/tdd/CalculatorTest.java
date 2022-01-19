package com.demo.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CalculatorTest {
    private DatabaseService databaseServiceMock;
    private Calculator calculator;

    @BeforeEach
    public void create() {
        databaseServiceMock = mock(DatabaseService.class);
        calculator = new Calculator(databaseServiceMock);
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

        // act
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            calculator.add("-1,-2");
        }, "Negatives not allowed: -1, -2");

        // assert
        Assertions.assertEquals(expectedExceptionMessage, thrown.getMessage());
    }

    // Verify that every time calculator.add(numbers) is called db.insert(numbers) is also called
    @Test
    public void checkDBMethodCall() {
        // arrange

        // act
        calculator.add("1,2");

        // assert
        verify(databaseServiceMock).insert("1,2");
    }

    @Test
    public void checkNullNum() {
        // arrange
        long expectedNum = -1;

        // act
        long actualNum = calculator.add(null);

        // assert
        assertEquals(expectedNum, actualNum);
    }
}
