package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование класса Calculator.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class CalculatorTest {

    /**
     * Тестирование сложения методом add().
     */
    @Test
    public void whenAddThenAdding() {
        Calculator calculator = new Calculator();
        calculator.add(1D, 1D);
        double result = calculator.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование вычитания методом subtract().
     */
    @Test
    public void whenSubtractThenSubtracting() {
        Calculator calculator = new Calculator();
        calculator.subtract(3D, 2D);
        double result = calculator.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование деления методом div().
     */
    @Test
    public void whenDivideThenDividing() {
        Calculator calculator = new Calculator();
        calculator.div(12D, 6D);
        double result = calculator.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование умножения методом multiply().
     */
    @Test
    public void whenMultiplyThenMultiplying() {
        Calculator calculator = new Calculator();
        calculator.multiply(2D, 2D);
        double result = calculator.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }
}
