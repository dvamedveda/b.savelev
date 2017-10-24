package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Factorial.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class FactorialTest {

    /**
     * Тест метода Factorial.calc().
     * Проверяет расчет факториала для числа больше нуля.
     */
    @Test
    public void whenGiveFiveThenReturnsFactorialFive() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(10);
        assertThat(result, is(3628800));
    }

    /**
     * Тест метода Factorial.calc().
     * Проверяет расчет факториала для нуля.
     */
    @Test
    public void whenGiveZeroThenReturnsOne() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        assertThat(result, is(1));
    }

}