package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Turn.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class TurnTest {

    /**
     * Тест метода Turn.back().
     * Проверяет, что метод правильно разворачивает массив
     * с четным количеством элементов.
     */
    @Test
    public void whenTurnsEvenArrayThenArrayTurnsRight() {
        Turn turn = new Turn();
        int[] ourArray = new int[]{1, 2, 3, 4};
        int[] result = turn.back(ourArray);
        int[] expected = new int[]{4, 3, 2, 1};
        assertThat(result, is(expected));
    }

    /**
     * Тест метода Turn.back().
     * Проверяет, что метод правильно разворачивает массив
     * с нечетным количеством элементов.
     */
    @Test
    public void whenTurnsOddArrayThenArrayTurnsRight() {
        Turn turn = new Turn();
        int[] ourArray = new int[]{1, 2, 3, 4, 5};
        int[] result = turn.back(ourArray);
        int[] expected = new int[]{5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }
}