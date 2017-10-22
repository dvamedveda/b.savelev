package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс, содержащий тесты для метода Max.max().
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class MaxTest {

    /**
     * Тест, проверящий поведение метода Max.max().
     * Проверяет случай, когда максимумом является первый параметр.
     */
    @Test
    public void whenFirstMaxThenReturnFirst() {
        Max max = new Max();
        int expected = 3;
        int result = max.max(3, 1);
        assertThat(expected, is(result));
    }

    /**
     * Тест, проверящий поведение метода Max.max().
     * Проверяет случай, когда максимумом является второй параметр.
     */
    @Test
    public void whenSecondMaxThenReturnsSecond() {
        Max max = new Max();
        int expected = 4;
        int result = max.max(1, 4);
        assertThat(expected, is(result));
    }

    /**
     * Тест проверящющий поведение метода Max.max().
     * Проверяет случай, когда максимальным является третий параметр.
     */
    @Test
    public void whenThirdMaxThenReturnsThird() {
        Max max = new Max();
        int expected = 11;
        int result = max.max(1, 4, 11);
        assertThat(expected, is(result));
    }
}