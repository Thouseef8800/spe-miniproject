package com.thouseef.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    public void testSquareRoot() {
        assertEquals(4.0, calc.squareRoot(16), 0.001);
    }

    @Test
    public void testFactorial() {
        assertEquals(120, calc.factorial(5));
    }

    @Test
    public void testNaturalLog() {
        assertEquals(1.0, calc.naturalLog(Math.E), 0.001);
    }

    @Test
    public void testPower() {
        assertEquals(8.0, calc.power(2, 3), 0.001);
    }
}